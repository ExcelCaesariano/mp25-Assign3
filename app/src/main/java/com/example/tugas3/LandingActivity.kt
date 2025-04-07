// LandingActivity.kt
package com.example.tugas3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val user = intent.getParcelableExtra<User>("user")
        val welcomeText = findViewById<TextView>(R.id.textWelcome)
        val moodText = findViewById<TextView>(R.id.textMood)
        val happyBtn = findViewById<Button>(R.id.buttonHappy)
        val neutralBtn = findViewById<Button>(R.id.buttonNeutral)
        val sadBtn = findViewById<Button>(R.id.buttonSad)

        welcomeText.text = "Welcome, ${user?.name}!"

        happyBtn.setOnClickListener {
            moodText.text = "Your mood: Happy 😊"
        }

        neutralBtn.setOnClickListener {
            moodText.text = "Your mood: Neutral 😐"
        }

        sadBtn.setOnClickListener {
            moodText.text = "Your mood: Sad 😢"
        }
    }
}
