package com.example.neuropredict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val historyList = mutableListOf<HistoryItem>()

        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))
        historyList.add(HistoryItem("2323"))






        val recyclerView = findViewById<RecyclerView>(R.id.recycler_history)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(applicationContext, historyList)


    }
}