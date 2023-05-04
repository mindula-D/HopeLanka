package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hopelankatest.R

class LoginSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)

        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
        btnGetStarted.setOnClickListener{
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }
    }
}