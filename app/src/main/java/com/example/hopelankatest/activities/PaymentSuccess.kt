package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.hopelankatest.R
import com.example.hopelankatest.adapters.CardAdapter
import com.example.hopelankatest.models.CardModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import java.util.Objects

class PaymentSuccess : AppCompatActivity() {
    // Declare variables for the fields in the layout
    private lateinit var totalDonations: TextView
    // Declare a variable for the database reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        // Assign the views to the corresponding variables using their id
        totalDonations = findViewById(R.id.totalDonation)

        // Get a reference to the "DonationAmount" node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("DonationAmount")
        // Attach a value event listener
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // Initialize a variable to hold the total donation amount
                var sum = 0

                // Iterate through the snapshot to calculate the total donation amount
                for (ds in snapshot.children) {
                    val map = ds.value as? Map<String, Any>
                    val amount = map?.get("amount")
                    val pValue = amount?.toString()?.toIntOrNull() ?: 0
                    // Add the "amount" value to the total donation amount
                    sum += pValue
                }

                // Display the total donation amount in the totalDonations TextView
                totalDonations.setText("Rs. " + sum.toString() + ".00/=")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        // Set a click listener for the OK button
        val ok = findViewById<Button>(R.id.ok)
        ok.setOnClickListener{
            val intent = Intent(this, EnterAmount::class.java)
            startActivity(intent)
        }
    }
}
