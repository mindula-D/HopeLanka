package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.splashscreen.R

class HomeAbout : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_about)


        //SOCIAL MEDIA LINKING
        //Social media link - Facebook
        val imgFacebook = findViewById<ImageView>(R.id.imgFacebook)
        imgFacebook.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://www.facebook.com/jayadinu.diaz?mibextid=ZbWKwL"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        //Social media link - Instagram
        val imgInstagram = findViewById<ImageView>(R.id.imgInstagram)
        imgInstagram.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://instagram.com/itz.me.jd?igshid=ZGUzMzM3NWJiOQ=="
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        //Social media link - LinkedIn
        val imgLinkedin = findViewById<ImageView>(R.id.imgLinkedin)
        imgLinkedin.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://www.linkedin.com/in/jayadinu-dias"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        //Social media link - Twitter
        val imgTwitter = findViewById<ImageView>(R.id.imgTwitter)
        imgTwitter.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://twitter.com/jdxiya?t=eJtekqedMVqleCMow4h1aw&s=09"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        //Social media link - Tiktok
        val imgTiktok = findViewById<ImageView>(R.id.imgTiktok)
        imgTiktok.setOnClickListener {
            // Perform the action when the image is clicked
            val url = "https://www.tiktok.com/@jayadinudiaz760?_t=8cCir4QjoDS&_r=1"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


        //BOTTOM NAVIGATION BAR
        //Navigate to home tab
        val theFeedText = findViewById<TextView>(R.id.theFeed)
        theFeedText.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
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