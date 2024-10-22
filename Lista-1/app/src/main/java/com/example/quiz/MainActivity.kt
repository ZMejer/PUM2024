package com.example.quiz

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioButtons = listOf(R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4)
        for (id in radioButtons) {
            val radioButton = findViewById<RadioButton>(id)
            radioButton.buttonTintList = ColorStateList.valueOf(Color.parseColor("#486405"))
        }

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#dde6c5"))
    }
}
