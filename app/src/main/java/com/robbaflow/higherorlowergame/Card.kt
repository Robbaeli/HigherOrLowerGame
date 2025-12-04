package com.robbaflow.higherorlowergame

class Card(val value: Int, val suit: String, val isRed: Boolean) {

    // Funktion som gör om siffror till text (14 -> "A", 13 -> "K")
    fun getDisplayValue(): String {
        if (value == 11) return "J"
        if (value == 12) return "Q"
        if (value == 13) return "K"
        if (value == 14) return "A"
        return value.toString()
    }
}

