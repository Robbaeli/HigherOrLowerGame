package com.robbaflow.higherorlowergame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// I filen RulesActivity.kt
class RulesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // HÄR kopplar du din RulesActivity-kod till din rules.xml-layout
        setContentView(R.layout.activity_rules)

    }
}