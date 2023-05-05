package com.example.text.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.text.R

class DeleteWarningDonation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_warning_donation)

        // Getting the "yes" button from the layout file using its ID
        val donationdelete = findViewById<Button>(R.id.yes)

        // Setting a click listener for the "yes" button
        donationdelete.setOnClickListener {
            // Creating an intent to start the FetchingName activity
            val intent = Intent(this, FetchingName::class.java)
            // Starting the FetchingName activity
            startActivity(intent)
        }
    }
}
