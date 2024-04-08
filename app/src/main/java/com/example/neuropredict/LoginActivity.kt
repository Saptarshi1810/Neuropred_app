package com.example.neuropredict

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val patientId=findViewById<TextInputEditText>(R.id.patient_id)
        val sendButton=findViewById<CardView>(R.id.send_button)

        sendButton.setOnClickListener {

            if(patientId.text!=null && patientId.text!!.isNotBlank()) {
                val intent = Intent(this, MainActivity::class.java)
                val apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
                val sp = getSharedPreferences("Login", MODE_PRIVATE)
                val ed = sp.edit()
                ed.putInt("ID",patientId.text.toString().toInt());
                ed.commit();
                val user=User(patientId.text.toString().toInt())
                val call: Call<Void> = apiInterface.getUserInformation(user)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Please try again later!!"+t.message, Toast.LENGTH_SHORT).show()
                    }
                })

            }else{
                Toast.makeText(this, "Please Enter the Patient Id.!!!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

fun <T> Call<T>.enqueue(any: Any) {

}
