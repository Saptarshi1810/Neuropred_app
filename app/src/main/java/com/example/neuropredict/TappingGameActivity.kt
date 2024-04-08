package com.example.neuropredict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TappingGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapping_game)
        var counter=0
        val timer=findViewById<TextView>(R.id.timer)
        val score=findViewById<TextView>(R.id.score)
        val startBtn=findViewById<CardView>(R.id.start_btn)
        val resetBtn=findViewById<CardView>(R.id.reset_btn)
        val backBtn=findViewById<ImageView>(R.id.back_btn)
        val tappingBtn=findViewById<CardView>(R.id.tapping_btn)
        val intertap=findViewById<TextView>(R.id.intertap)
        var fin =1
        var ti : Long = 0
        var interTapTime : Long =0
        var lastTime : Long =15000

        tappingBtn.setOnClickListener {
            if(fin==0)
                counter++
            score.text = ""+counter
            //var temp = Integer.parseInt(timer.text.toString())
            interTapTime+=lastTime - ti
            lastTime=ti
            //intertap.text=interTapTime.toString()


        }
        backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        resetBtn.setOnClickListener {
            timer.text= "15"
            counter=0
            score.text="0"
            startBtn.visibility=View.VISIBLE
            resetBtn.visibility=GONE
            intertap.visibility=GONE


        }
        startBtn.setOnClickListener {
                fin=0
                resetBtn.visibility=GONE
                startBtn.visibility=GONE
                object : CountDownTimer(15000, 1000){
                    override fun onTick(p0: Long) {
                        timer.text= (p0/1000).toString()
                        ti=p0
                    }

                    override fun onFinish() {
                        timer.text="0"
                        val ans = String.format("%.2f", (interTapTime.toDouble()/(1000*counter))).toDouble()
                        intertap.text = "Inter Tap Time:" + ans.toString()+"s"
                        resetBtn.visibility= View.VISIBLE
                        intertap.visibility= View.VISIBLE
                        fin=1
                    }
                }.start()
        }
    }
}