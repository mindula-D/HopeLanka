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

        // Find the ImageView with id `home` in the layout
        val imageView = findViewById<ImageView>(R.id.home)

        // Set a click listener on the `imageView` object to navigate to `Itemm` activity
        imageView.setOnClickListener {
            val intent = Intent(this, Itemm::class.java)
            startActivity(intent)
        }

        // Find the Button with id `ok` in the layout
        val doneee = findViewById<Button>(R.id.ok)
        // Set a click listener on the `doneee` object to navigate to `Itemm` activity
        doneee.setOnClickListener {
            val intent = Intent(this, Itemm::class.java)
            startActivity(intent)

        }
    }}