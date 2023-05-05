package com.example.text.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.text.models.DonationModel
import com.example.text.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Itemm : AppCompatActivity() {
    private lateinit var name: TextInputLayout
    private lateinit var number: TextInputLayout
    private lateinit var typee: Spinner
    private lateinit var email: TextInputLayout
    private lateinit var description: TextInputLayout
    private lateinit var myButton: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        name = findViewById(R.id.textInputLayout4)
        number = findViewById(R.id.textInputLayout7)
        typee = findViewById(R.id.spinner3)
        email = findViewById(R.id.textInputLayout8)
        description = findViewById(R.id.textInputLayout5)
        myButton = findViewById(R.id.buttonn)

        dbRef = FirebaseDatabase.getInstance().getReference("ItemDonation")

        myButton.setOnClickListener {
            saveDonation()
        }

        val Donations = arrayOf("Clothes", "Food", "Education", "Electronics", "Medicine")
        val spinner = findViewById<Spinner>(R.id.spinner3)
        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item,
            Donations
        )
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext, "Selected donation type is = " + Donations[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

//    private fun saveDonation(){
//        val Name= name.editText?.text.toString()
//        val Number= number.editText?.text.toString()
//        val Email= email.editText?.text.toString()
//        val Description= description.editText?.text.toString()
//        val Typee = typee.selectedItem.toString()
//
//        if(Name.isEmpty() || Number.isEmpty() || Email.isEmpty() || Description.isEmpty() ||
//            Typee.isEmpty()  ) {
//            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if(!Email.contains("@")) {
//            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//
//        val donationId = dbRef.push().key!!
//        val donation = DonationModel(donationId,Name,Number,Email,Description,Typee)
//
//        dbRef.child(donationId).setValue(donation)
//            .addOnCompleteListener{
//                Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show()
//                name.editText?.text?.clear()
//                number.editText?.text?.clear()
//                email.editText?.text?.clear()
//                description.editText?.text?.clear()
//                typee.setSelection(0)
//
//
//            }.addOnFailureListener{err->
//                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
//            }
//        val intent = Intent(this, FetchingName::class.java)
//        startActivity(intent)
//    }

    private fun saveDonation(){
        val Name= name.editText?.text.toString()
        val Number= number.editText?.text.toString()
        val Email= email.editText?.text.toString()
        val Description= description.editText?.text.toString()
        val Typee = typee.selectedItem.toString()

        if(Name.isEmpty()) {
            name.error = "Please enter a name"
            return
        } else {
            name.error = null
        }

        if(Number.isEmpty()) {
            number.error = "Please enter a number"
            return
        } else {
            number.error = null
        }

        if(Email.isEmpty()) {
            email.error = "Please enter an email address"
            return
        } else if(!Email.contains("@")) {
            email.error = "Please enter a valid email address"
            return
        } else {
            email.error = null
        }

        if(Description.isEmpty()) {
            description.error = "Please enter a description"
            return
        } else {
            description.error = null
        }

        if(Typee.isEmpty()) {
            Toast.makeText(this, "Please select a donation type", Toast.LENGTH_SHORT).show()
            return
        }

        val donationId = dbRef.push().key!!
        val donation = DonationModel(donationId,Name,Number,Email,Description,Typee)

        dbRef.child(donationId).setValue(donation)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show()
                name.editText?.text?.clear()
                number.editText?.text?.clear()
                email.editText?.text?.clear()
                description.editText?.text?.clear()
                typee.setSelection(0)


            }.addOnFailureListener{err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
        val intent = Intent(this, FetchingName::class.java)
        startActivity(intent)
    }

}