package com.robbaflow.higherorlowergame

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    // Variabler för att minnas spelet
    // En lista som håller alla kort i leken
    var deck = mutableListOf<Card>()

    // Kortet som syns på skärmen just nu
    var currentCard: Card? = null

    // Spelarens poäng
    var score = 0

    // Variabler för UI
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

        // Starta spelet
        setupNewGame()

        // klicka på knapp higher
        btnHigher.setOnClickListener {
            checkGuess(true) // true betyder "Jag tror högre"
        }

        // klicka på knapp lower

        btnLower.setOnClickListener {
            checkGuess(false) // false betyder "Jag tror lägre"
        }
    }

    // Funktion: Skapa kortlek och nollställ poäng
    fun setupNewGame() {
        score = 0
        deck.clear() // Töm listan efter vi gått igenom hela

        // Skapa 52 kort med loopar
        val suits = listOf("♥", "♦", "♣", "♠")

        for (suit in suits) {
            // Bestäm om kortet är rött eller svart
            val isRed = (suit == "♥" || suit == "♦")

            // Loopa värden 2 till 14
            for (value in 2..14) {
                deck.add(Card(value, suit, isRed))
            }
        }

        deck.shuffle() // Blanda kortleken slumpmässigt

        // Ta första kortet och visa det
        if (deck.isNotEmpty()) {
            currentCard = deck[0]
            deck.removeAt(0) // Ta bort det från leken
            updateUI() // Visa på skärmen
        }
    }

    // Funktion: Kollar om gissningen var rätt
    fun checkGuess(guessedHigher: Boolean) {
        // Om kortleken är slut, starta om
        if (deck.isEmpty()) {
            Toast.makeText(this, "Game Over! Starting new game.", Toast.LENGTH_LONG).show()
            setupNewGame()
            return
        }

        // Ta nästa kort i listan
        val nextCard = deck[0]
        deck.removeAt(0) // Ta bort det så det inte kommer igen

        // Jämför värdena
        val oldValue = currentCard!!.value
        val newValue = nextCard.value

        var isCorrect = false

        if (guessedHigher) {
            // Om man gissade högre, måste nya vara >= gamla
            if (newValue >= oldValue) isCorrect = true
        } else {
            // Om man gissade lägre, måste nya vara <= gamla
            if (newValue <= oldValue) isCorrect = true
        }

        // Ge poäng eller inte
        if (isCorrect) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        // Byt så att nästa kort blir det nuvarande
        currentCard = nextCard
        updateUI()
    }

    // Funktion: Uppdaterar texten på skärmen
    fun updateUI() {
        tvScore.text = "Score: $score"

        // Visa rätt symbol och värde
        // !! betyder att vi lovar Kotlin att currentCard inte är null här
        tvCardSymbol.text = currentCard!!.suit
        tvCardValue.text = currentCard!!.getDisplayValue()

        // Ändra färg till Röd eller Svart
        if (currentCard!!.isRed) {
            tvCardSymbol.setTextColor(Color.RED)
            tvCardValue.setTextColor(Color.RED)
        } else {
            tvCardSymbol.setTextColor(Color.BLACK)
            tvCardValue.setTextColor(Color.BLACK)
        }
    }
}