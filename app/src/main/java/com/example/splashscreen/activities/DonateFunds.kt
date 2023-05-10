package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.splashscreen.R

class DonateFunds : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_funds)


        //Click here text (Terms & Conditions)
        val clickHereNew = findViewById<TextView>(R.id.clickHere2)
        clickHereNew.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://docs.google.com/document/d/e/2PACX-1vSdO-_VQbjFog01uGzKTZmosXfTZ4qxv9SZB3rAFDs1y0J-8aRW-9JYTEK4PrimXAUoKymOceX0dIHd/pub"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


        //BOTTOM NAVIGATION BAR
        //Navigate to Donate Tab
        val itemsText = findViewById<TextView>(R.id.donateItems)
        itemsText.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }

        //Move to Donate Remind Manager Page
        val addImage = findViewById<ImageView>(R.id.addButton)
        addImage.setOnClickListener {
            val Intent = Intent(this, RemindManager::class.java)
            startActivity(Intent)
        }

        //Move to Donate Remind Manager Page
        val addText = findViewById<TextView>(R.id.reminder)
        addText.setOnClickListener {
            val Intent = Intent(this, RemindManager::class.java)
            startActivity(Intent)
        }

        //Move to Overview Tab
        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

        //Move to Home Tab
        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }
    }
}