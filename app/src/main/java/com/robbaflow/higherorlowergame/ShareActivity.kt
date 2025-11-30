package com.robbaflow.higherorlowergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val score = intent.getIntExtra("score", 0)

        val tvFinalScore = findViewById<TextView>(R.id.tvFinalScore)
        val btnShare = findViewById<Button>(R.id.btnShare)

        tvFinalScore.text = "Ditt resultat: $score"

        btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Jag fick $score poäng i Högre eller Lägre!")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Dela med"))
        }
    }
}
