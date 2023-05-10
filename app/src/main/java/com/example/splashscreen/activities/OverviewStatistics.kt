package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.splashscreen.R

class OverviewStatistics : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview_statistics)

        val emailUsText = findViewById<TextView>(R.id.emailUs)
        emailUsText.setOnClickListener {
            val intent = Intent(this, SendEmail::class.java)
            startActivity(intent)
        }

        val emailBlock = findViewById<TextView>(R.id.blockTwoEmail)
        emailBlock.setOnClickListener {
            val intent = Intent(this, SendEmail::class.java)
            startActivity(intent)
        }

        //Navigate to Overview Tab
        val projectsText = findViewById<TextView>(R.id.projects)
        projectsText.setOnClickListener {
            val Intent = Intent(this, OverviewProjects::class.java)
            startActivity(Intent)
        }

        //Navigate to Home Tab
        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }

        //Navigate to Donate Tab
        val donateTab = findViewById<ImageView>(R.id.donate)
        donateTab.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }
    }
}