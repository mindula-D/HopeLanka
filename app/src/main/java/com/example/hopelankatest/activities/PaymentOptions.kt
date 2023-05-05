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

        //get the value of amount from EnterAmount
        val amount = intent.getStringExtra("amount")

        // find the total textview
        val total = findViewById<TextView>(R.id.total).apply {
            //assign the value of amount
            text = "Rs. " + amount + ".00/="
        }

        // Set a click listener for the payNow button
        val payNow = findViewById<Button>(R.id.payNow)
        payNow.setOnClickListener{
            val intent = Intent(this, PaymentSuccess::class.java)
            startActivity(intent)
        }

        // Set a click listener for the back button
        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener{
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }
    }
}