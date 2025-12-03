package com.robbaflow.higherorlowergame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Denna ENUM används för att veta vilken knapp som klickades på
enum class GuessType { HIGHER, LOWER }


class GameActivity : AppCompatActivity() {

    // 1. Instansvariabler (Spelets tillstånd)
    // Dessa deklareras HÖGST UPP I KLASSEN så att ALLA funktioner kan komma åt dem.
    private val deck = Deck()
    private var currentCard: Card? = null
    private var score: Int = 0

    // --- onCreate-funktionen ---
    // Denna körs EN GÅNG när skärmen startas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Starta spelet:
        currentCard = deck.drawCard() // Dra första kortet
        updateUiWithCard(currentCard) // Visa kortet
        setupListeners()              // Gör knapparna klickbara
    }

    // --- setupListeners-funktionen ---
    // Denna gör så att knapparna "lyssnar" på klick
    private fun setupListeners() {
        // Hämta knapparna från XML med deras ID
        val higherButton: Button = findViewById(R.id.higherButton)
        val lowerButton: Button = findViewById(R.id.lowerButton)

        // Koppla klicket till vår gemensamma logik-funktion
        higherButton.setOnClickListener {
            handleGuess(GuessType.HIGHER)
        }
        lowerButton.setOnClickListener {
            handleGuess(GuessType.LOWER)
        }
    }

    // --- handleGuess-funktionen ---
    // Denna innehåller KÄRNLOGIKEN för varje gissning
    private fun handleGuess(guess: GuessType) {
        val oldCard = currentCard      // Det kort vi jämför mot
        val nextCard = deck.drawCard() // Dra nästa kort

        if (oldCard == null || nextCard == null) {
            // Leken är slut eller ett fel inträffade
            updateUiWithCard(null)
            return
        }

        // Kollar om gissningen stämmer
        val isGuessCorrect: Boolean = when (guess) {
            GuessType.HIGHER -> nextCard.value > oldCard.value
            GuessType.LOWER -> nextCard.value < oldCard.value
        }

        updateScore(isGuessCorrect) // Uppdatera poängen
        currentCard = nextCard      // Sätt det nya kortet som aktuellt
        updateUiWithCard(currentCard) // Uppdatera skärmen
    }

    // --- updateScore-funktionen ---
    private fun updateScore(isCorrect: Boolean) {
        if (isCorrect) {
            score++
            // Toast.makeText(this, "Rätt gissat!", Toast.LENGTH_SHORT).show()
        } else {
            score = 0
            // Toast.makeText(this, "Fel! Poängen nollställs.", Toast.LENGTH_SHORT).show()
        }

        // Uppdatera TextView för poängen (måste ha ID:t scoreTextView i XML)
        findViewById<TextView>(R.id.scoreTextView).text = "Score = $score"
    }

    // --- updateUiWithCard-funktionen ---
    // Denna uppdaterar vad användaren ser (kortet och kort kvar)
    private fun updateUiWithCard(card: Card?) {
        val cardTextView = findViewById<TextView>(R.id.currentCardValue)
        val cardsLeftTextView = findViewById<TextView>(R.id.cardsLeftTextView)

        if (card != null) {
            // Visa kortets värde och färg
            cardTextView.text = "${card.value} ${getSuitSymbol(card.suit)}"
        } else {
            // Game Over
            cardTextView.text = "Game Over!"
            // Här kan vi lägga till logik för att visa slutpoäng/menyn
        }

        cardsLeftTextView.text = "Cards left = ${deck.cardsRemaining()}"
    }

    // --- Hjälpfunktioner ---
    private fun getSuitSymbol(suit: String): String {
        return when (suit) {
            "Hearts" -> "♥"
            "Diamonds" -> "♦"
            "Clubs" -> "♣"
            "Spades" -> "♠"
            else -> ""
        }
    }
}