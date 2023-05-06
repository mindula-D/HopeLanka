package com.example.splashscreen.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.splashscreen.R

class MainActivity : AppCompatActivity() {

    private val  SPLASH_TIME: Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed( {
            startActivity(Intent (this, HomeFeed::class.java))
            finish()
        }, SPLASH_TIME)

    }
}