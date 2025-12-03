package com.robbaflow.higherorlowergame

// Deck.kt
class Deck {
    //använder en MutableList (en lista som går att ändra) för kortleken.
    private val deck: MutableList<Card> = mutableListOf()

    init {
        // 1. Skapa korten
        createDeck()
        // 2. Blanda korten direkt när leken är skapad
        shuffle()
    }

    private fun createDeck() {
        val suits = listOf("Hearts", "Diamonds", "Clubs", "Spades")
        // Värdena 2 till 14 (Ess)
        val values = 2..14

        // Skapa alla 52 kombinationer
        for (suit in suits) {
            for (value in values) {
                deck.add(Card(value, suit))
            }
        }
    }

    // Funktion för att blanda leken
    fun shuffle() {
        deck.shuffle() // Funktion för att blanda en lista
    }

    // Funktion för att dra det översta kortet
    fun drawCard(): Card? {

        return if (deck.isNotEmpty()) {
            deck.removeAt(0) // Tar bort och returnerar det första kortet
        } else {
            null // Returnerar null (inget kort) om leken är tom
        }
    }

    // Funktion för att se hur många kort som är kvar i leken
    fun cardsRemaining(): Int {
        return deck.size
    }
}