package com.robbaflow.higherorlowergame

// Card.kt
// Representerar ett enda kort i leken.
data class Card(
    val value: Int, // Värdet (t.ex. 2-14, där 11=Knekt, 12=Dam, 13=Kung, 14=Ess)
    val suit: String // Färgen (t.ex. "Hearts", "Spades", "Diamonds", "Clubs")
)