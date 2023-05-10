package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.splashscreen.R

class OverviewProjects : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview_projects)


        //Navigate to email send
        val emailUsBlock = findViewById<TextView>(R.id.blockTwoEmailUs)
        emailUsBlock.setOnClickListener {
            val intent = Intent(this, SendEmail::class.java)
            startActivity(intent)
        }

        //Navigate to Overview Tab
        val statsText = findViewById<TextView>(R.id.statistics)
        statsText.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
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