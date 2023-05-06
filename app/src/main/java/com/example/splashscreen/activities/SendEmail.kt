package com.example.splashscreen.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.splashscreen.R
import com.example.splashscreen.databinding.ActivitySendEmailBinding

class SendEmail : AppCompatActivity() {

    lateinit var binding : ActivitySendEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }

        val donateTab = findViewById<ImageView>(R.id.donate)
        donateTab.setOnClickListener {
            val Intent = Intent(this, DonateItems::class.java)
            startActivity(Intent)
        }

     binding.sendBtn.setOnClickListener {

         //Get all input values
         val email = binding.emailAddress.text.toString()
         val subject = binding.subject.text.toString()
         val message = binding.message.text.toString()

         //If more than one email address, email addresses are separated by commas
         val addresses = email.split(",".toRegex()).toTypedArray()

         //Intent object
         val intent = Intent(Intent.ACTION_SENDTO).apply {

             data = Uri.parse("mailto:") //Open email apps
             //Put all the data
             putExtra(Intent.EXTRA_EMAIL,addresses)
             putExtra(Intent.EXTRA_SUBJECT,subject)
             putExtra(Intent.EXTRA_TEXT,message)

         }

            //Starts the Activity
             startActivity(intent)

     }
    }
}