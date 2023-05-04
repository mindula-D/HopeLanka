package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.hopelankatest.R

class PaymentOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_options)

        val amount = intent.getStringExtra("amount")
        val total = findViewById<TextView>(R.id.total).apply {
            text = "Rs. " + amount + ".00/="
        }

        val payNow = findViewById<Button>(R.id.payNow)
        payNow.setOnClickListener{
            val intent = Intent(this, PaymentSuccess::class.java)
            startActivity(intent)
        }

        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener{
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }
    }
}