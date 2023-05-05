package com.example.text.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.text.R

class DonationSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_success)

        val imageView = findViewById<ImageView>(R.id.home)

        imageView.setOnClickListener {
            val intent = Intent(this, Itemm::class.java)
            startActivity(intent)
        }

        val doneee = findViewById<Button>(R.id.ok)
        doneee.setOnClickListener {
            val intent = Intent(this, Itemm::class.java)
            startActivity(intent)

        }
    }}