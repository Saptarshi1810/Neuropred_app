package com.example.neuropredict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_history)
        val historyList = mutableListOf<HistoryItem>()

        val apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        val sp1 = getSharedPreferences("Login", MODE_PRIVATE)
        val id = sp1.getInt("ID",0);
        val call: Call<List<scoreItem>> = apiInterface.getTappingInformation(id)
        call.enqueue(object : Callback<List<scoreItem>> {
            override fun onResponse(call: Call<List<scoreItem>>, response: Response<List<scoreItem>>) {
                val listofitem=response.body()
                if(listofitem.isNullOrEmpty()){
                    Toast.makeText(this@HistoryActivity, "Please try again later!!", Toast.LENGTH_SHORT).show()
                }
                else{
                    for (item in listofitem){
                        val x = ((item.Tap_no*2)+(item.median_inter_tap_1*10)+(1.0*item.CorrectTap)-(1.0*item.IncorrectTap)+(5*item.median_inter_tap_2)-(item.Offset_distance*6.9))*2.5
                        historyList.add(HistoryItem(x.toString()))
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<List<scoreItem>>, t: Throwable) {
                Toast.makeText(this@HistoryActivity, "Please try again later!!"+t.message, Toast.LENGTH_SHORT).show()
            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(applicationContext, historyList)


    }
}