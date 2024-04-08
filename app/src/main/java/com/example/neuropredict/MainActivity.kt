package com.example.neuropredict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tappingGameButton=findViewById<CardView>(R.id.tapping_game_btn)
        val identifyGameButton=findViewById<CardView>(R.id.identify_game_btn)
        tappingGameButton.setOnClickListener{
            val intent = Intent(this,TappingGameActivity::class.java)
            startActivity(intent)
        }
        identifyGameButton.setOnClickListener{
            val intent = Intent(this,IdentifyGameActivity::class.java)
            startActivity(intent)
        }
    }
}