package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hopelankatest.R

class DeleteWarningCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_warning_card)

        // Set a click listener for the No button
        val btnYes = findViewById<Button>(R.id.yes)
        btnYes.setOnClickListener{
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }

        // Set a click listener for the Yes button
        val btnNo = findViewById<Button>(R.id.no)
        btnNo.setOnClickListener{
            val intent = Intent(this, CardDetails::class.java)
            startActivity(intent)
        }
    }
}