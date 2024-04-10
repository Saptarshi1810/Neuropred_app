package com.example.neuropredict

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tappingGameButton=findViewById<CardView>(R.id.tapping_game_btn)
        val identifyGameButton=findViewById<CardView>(R.id.identify_game_btn)
        val historyButton = findViewById<CardView>(R.id.history)
        val bookAppointment =findViewById<CardView>(R.id.appointment)
        tappingGameButton.setOnClickListener{
            val intent = Intent(this,TappingGameActivity::class.java)
            startActivity(intent)
        }
        identifyGameButton.setOnClickListener{
            val intent = Intent(this,IdentifyGameActivity::class.java)
            startActivity(intent)
        }
        historyButton.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }

        bookAppointment.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://neurokolkata.org/appointment/"))
            startActivity(browserIntent)
        }
    }
}