package com.robbaflow.higherorlowergame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hitta knapparna i XML
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnRules = findViewById<Button>(R.id.rules) // (Bra att du använder R.id.)


        // Vad händer när man klickar?

        btnStart.setOnClickListener {
            // Skapa Intent för GameActivity
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        btnRules.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }
    }
}