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

    private lateinit var totalDonations: TextView
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        totalDonations = findViewById(R.id.totalDonation)
        dbRef = FirebaseDatabase.getInstance().getReference("DonationAmount")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var sum = 0

                for (ds in snapshot.children) {
                    val map = ds.value as? Map<String, Any>
                    val amount = map?.get("amount")
                    val pValue = amount?.toString()?.toIntOrNull() ?: 0
                    sum += pValue
                }

                totalDonations.setText("Rs. " + sum.toString() + ".00/=")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val ok = findViewById<Button>(R.id.ok)
        ok.setOnClickListener{
            val intent = Intent(this, EnterAmount::class.java)
            startActivity(intent)
        }
    }
}

//                int sum = 0;
//
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                    Object amount = map.get("amount");
//                    int pValue = Integer.parseInt(String, valueOf(amount));
//                    sum += pValue;
//
//                    totalDonations.setText(String.valueOf(sum));
//                }