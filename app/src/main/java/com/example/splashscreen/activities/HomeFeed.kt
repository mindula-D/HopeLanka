package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.splashscreen.R

class HomeFeed : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed)


        //BOTTOM NAVIGATION BAR
        //Navigate to donate tab
        val donateButton = findViewById<Button>(R.id.donateButton)
        donateButton.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }

        //Navigate to home tab
        val aboutUsText = findViewById<TextView>(R.id.aboutUs)
        aboutUsText.setOnClickListener {
            val Intent = Intent(this, HomeAbout::class.java)
            startActivity(Intent)
        }

        //Navigate to donate tab
        val donateTab = findViewById<ImageView>(R.id.donate)
        donateTab.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }

        //Navigate to overview tab
        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

    }
}