package com.robbaflow.higherorlowergame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    var deck = mutableListOf<Card>()
    var currentCard: Card? = null
    var score = 0

    lateinit var tvScore: TextView
    lateinit var tvCardSymbol: TextView
    lateinit var tvCardValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // hitta xml-element
        tvScore = findViewById(R.id.tvScore)
        tvCardSymbol = findViewById(R.id.tvCardSymbol)
        tvCardValue = findViewById(R.id.tvCardValue)

        val btnHigher = findViewById<Button>(R.id.btnHigher)
        val btnLower = findViewById<Button>(R.id.btnLower)
        val btnShare = findViewById<Button>(R.id.btnShare)   // ⬅ SHARE-knapp

        // Starta spelet
        setupNewGame()

        btnHigher.setOnClickListener {
            checkGuess(true)
        }

        btnLower.setOnClickListener {
            checkGuess(false)
        }

        //SHARE KNAPPENS FUNKTION
        btnShare.setOnClickListener {
            val shareText = "Jag fick $score poäng i Higher or Lower! Kan du slå mig? 😄"

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }

            startActivity(Intent.createChooser(shareIntent, "Dela via"))
        }
    }

    fun setupNewGame() {
        score = 0
        deck.clear()

        val suits = listOf("♥", "♦", "♣", "♠")

        for (suit in suits) {
            val isRed = (suit == "♥" || suit == "♦")
            for (value in 2..14) {
                deck.add(Card(value, suit, isRed))
            }
        }

        deck.shuffle()

        if (deck.isNotEmpty()) {
            currentCard = deck[0]
            deck.removeAt(0)
            updateUI()
        }
    }

    fun checkGuess(guessedHigher: Boolean) {
        if (deck.isEmpty()) {
            Toast.makeText(this, "Game Over! Starting new game.", Toast.LENGTH_LONG).show()
            setupNewGame()
            return
        }

        val nextCard = deck[0]
        deck.removeAt(0)

        val oldValue = currentCard!!.value
        val newValue = nextCard.value

        val isCorrect = if (guessedHigher) newValue >= oldValue else newValue <= oldValue

        if (isCorrect) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        currentCard = nextCard
        updateUI()
    }

    fun updateUI() {
        tvScore.text = "Score: $score"

        tvCardSymbol.text = currentCard!!.suit
        tvCardValue.text = currentCard!!.getDisplayValue()

        if (currentCard!!.isRed) {
            tvCardSymbol.setTextColor(Color.RED)
            tvCardValue.setTextColor(Color.RED)
        } else {
            tvCardSymbol.setTextColor(Color.BLACK)
            tvCardValue.setTextColor(Color.BLACK)
        }
    }
}
