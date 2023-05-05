package com.example.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeFeed : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed)

        val donateButton = findViewById<Button>(R.id.donateButton)
        donateButton.setOnClickListener {
            val Intent = Intent(this,DonateItems::class.java)
            startActivity(Intent)
        }

        val aboutUsText = findViewById<TextView>(R.id.aboutUs)
        aboutUsText.setOnClickListener {
            val Intent = Intent(this, HomeAbout::class.java)
            startActivity(Intent)
        }

        val donateTab = findViewById<ImageView>(R.id.donate)
        donateTab.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }

        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

    }
}