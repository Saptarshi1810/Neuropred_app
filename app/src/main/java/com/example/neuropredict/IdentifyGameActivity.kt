package com.example.neuropredict

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs
import kotlin.math.sqrt


class IdentifyGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identify_game)
        var right=0
        var wrong=0
        val correctText=findViewById<TextView>(R.id.correct_text)
        val wrongText=findViewById<TextView>(R.id.wrong_text)
        val timer=findViewById<TextView>(R.id.timer)
        val startBtn=findViewById<CardView>(R.id.start_btn)
        val resetBtn=findViewById<CardView>(R.id.reset_btn)
        val backBtn=findViewById<ImageView>(R.id.back_btn)
        val tappingScreen=findViewById<CardView>(R.id.clickable_box)
        val c1=findViewById<CardView>(R.id.c1)
        val layout=findViewById<ConstraintLayout>(R.id.tapping_layout)
        val bottom = findViewById<TextView>(R.id.bottom_align)
        val intertap = findViewById<TextView>(R.id.intertap)
        val offsetDist = findViewById<TextView>(R.id.offset_distance)
        var ti : Long = 0
        var interTapTime : Long =0
        var lastTime : Long =30000
        var fin =1
        var randx=0
        var randy=0
        var k: Float = 0F
        var tx : Float =0F
        var ty : Float = 0F
        layout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {//Do Something
                        tx = event.x
                        ty = event.y
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        tappingScreen.setOnClickListener {
            k+= sqrt((abs(tx-randx)*abs(tx-randx)) + (abs(ty-randy)*abs(ty-randy)))
            wrong++

        }

        c1.setOnClickListener {
            right++
            randx=(40..bottom.x.toInt()-40).random()
            randy=(40..bottom.y.toInt()-40).random()
            c1.x= randx.toFloat()
            c1.y=randy.toFloat()
            interTapTime+=lastTime - ti
            lastTime=ti
        }




        backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        resetBtn.setOnClickListener {
            timer.text= "30"
            tappingScreen.visibility= VISIBLE
            correctText.visibility= GONE
            wrongText.visibility=GONE
            startBtn.visibility= View.VISIBLE
            resetBtn.visibility= View.GONE
            c1.visibility=GONE
            offsetDist.visibility= GONE
            intertap.visibility=GONE
            lastTime=30000
            k = 0F

        }

        startBtn.setOnClickListener {
            c1.visibility= VISIBLE
            randx=(40..bottom.x.toInt()-40).random()
            randy=(40..bottom.y.toInt()-40).random()
            c1.x= randx.toFloat()
            c1.y=randy.toFloat()

            fin=0
            right=0
            wrong=0
            resetBtn.visibility= View.GONE
            startBtn.visibility= View.GONE
            object : CountDownTimer(30000, 1000){
                override fun onTick(p0: Long) {
                    timer.text= (p0/1000).toString()
                    ti=p0
                }

                override fun onFinish() {
                    correctText.text="CORRECT: " + right.toString()
                    wrongText.text="WRONG: "+wrong.toString()
                    var ans : Double = 0.0
                    var ans2 : Double= 0.0
                    if(right!=0){
                         ans = String.format("%.2f", (interTapTime.toDouble()/(1000*right))).toDouble()
                    }
                    if (wrong!=0){
                        ans2 =String.format("%.2f", (k.toDouble()/(wrong*100))).toDouble()
                    }
                    intertap.text = "Inter-Tap Time: "+ans.toString()+"s"
                    offsetDist.text= "Offset Dist." + ans2.toString()+" pxl"
                    timer.text="0"
                    resetBtn.visibility= View.VISIBLE
                    fin=1
                    tappingScreen.visibility=GONE
                    correctText.visibility= VISIBLE
                    wrongText.visibility=VISIBLE
                    intertap.visibility= VISIBLE
                    offsetDist.visibility= VISIBLE
                    c1.visibility=GONE
                    val sp1 = getSharedPreferences("Login", MODE_PRIVATE)
                    val id = sp1.getInt("ID",0);
                    val apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
                    val tap = tappingItem(
                        right,
                        wrong,
                        ans,
                        ans,
                        ans2,
                        id,
                        right+wrong
                    )
                    val call: Call<Void> = apiInterface.postTappingInformation(tap)
                    call.enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(this@IdentifyGameActivity, "Please try again later!!"+t.message, Toast.LENGTH_SHORT).show()
                        }
                    })

                }
            }.start()
        }

    }
}