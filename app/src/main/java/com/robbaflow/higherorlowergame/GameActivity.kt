package com.robbaflow.higherorlowergame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score

class GameActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var tvCardsLeft: TextView
    private lateinit var tvCard: TextView
    private lateinit var btnHigher: Button
    private lateinit var btnLower: Button
    private lateinit var btnMenu: Button


    private  val deck = mutableListOf<String>()
    private val currentCardValue = 0
    private val score = 0
    private val cardsLeft = 52



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)


        tvScore = findViewById(R.id.tvScore)
        tvCardsLeft = findViewById(R.id.tvCardsLeft)
        tvCard = findViewById(R.id.tvCard)
        btnHigher = findViewById(R.id.btnHigher)
        btnLower = findViewById(R.id.btnLower)
        btnMenu = findViewById(R.id.btnMenu)

    }

}