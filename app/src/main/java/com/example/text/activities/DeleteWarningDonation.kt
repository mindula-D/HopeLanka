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

        val donationdelete = findViewById<Button>(R.id.yes)
        donationdelete.setOnClickListener {
            val intent = Intent(this, FetchingName::class.java)
            startActivity(intent)
        }
    }
}
