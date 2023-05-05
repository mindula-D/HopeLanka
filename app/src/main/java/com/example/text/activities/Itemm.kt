package com.example.text.activities
// Import required libraries and classes
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.text.models.DonationModel
import com.example.text.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Itemm : AppCompatActivity() {

    // Declare variables for the input fields, button, and database reference
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

        // Find the input fields, button, and spinner views by their IDs
        name = findViewById(R.id.textInputLayout4)
        number = findViewById(R.id.textInputLayout7)
        typee = findViewById(R.id.spinner3)
        email = findViewById(R.id.textInputLayout8)
        description = findViewById(R.id.textInputLayout5)
        myButton = findViewById(R.id.buttonn)

        // Get a reference to the "ItemDonation" node in the Firebase Realtime Database
        dbRef = FirebaseDatabase.getInstance().getReference("ItemDonation")

        // Set an OnClickListener for the button to save the donation data to the database
        myButton.setOnClickListener {
            saveDonation()
        }

        // Create an array of donation types and populate the spinner with the values
        val Donations = arrayOf("Clothes", "Food", "Education", "Electronics", "Medicine")
        val spinner = findViewById<Spinner>(R.id.spinner3)
        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item,
            Donations
        )
        spinner.adapter = arrayAdapter

        // Set an OnItemSelectedListener for the spinner to show the selected donation type
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

    // Define a function to save the donation data to the database
    private fun saveDonation(){
        // Get the values from the input fields and spinner
        val Name= name.editText?.text.toString()
        val Number= number.editText?.text.toString()
        val Email= email.editText?.text.toString()
        val Description= description.editText?.text.toString()
        val Typee = typee.selectedItem.toString()

        // Validate the input fields and spinner selection
        if(Name.isEmpty()) {
            name.error = "Please enter a name"
            // Display a Snackbar with the error message
            Snackbar.make(findViewById(android.R.id.content), "Please enter a name", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            name.error = null
        }

        if(Number.isEmpty()) {
            number.error = "Please enter a number"
            Snackbar.make(findViewById(android.R.id.content), "Please enter a number", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            number.error = null
        }

        if(Email.isEmpty()) {
            email.error = "Please enter an email address"
            Snackbar.make(findViewById(android.R.id.content), "Please enter an email", Snackbar.LENGTH_SHORT).show()
            return
        } else if(!Email.contains("@")) {
            email.error = "Please enter a valid email address"
            Snackbar.make(findViewById(android.R.id.content), "Please enter a valid email", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            email.error = null
        }

        if(Description.isEmpty()) {
            description.error = "Please enter a description"
            Snackbar.make(findViewById(android.R.id.content), "Please enter a description", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            description.error = null
        }

        if(Typee.isEmpty()) {
            Toast.makeText(this, "Please select a donation type", Toast.LENGTH_SHORT).show()
            return
        }

        // Generate a unique donation id and create a DonationModel object
        val donationId = dbRef.push().key!!
        val donation = DonationModel(donationId,Name,Number,Email,Description,Typee)

        // Save the donation data to the Firebase Realtime Database
        dbRef.child(donationId).setValue(donation)
            .addOnCompleteListener{
                // Show a success message and clear the input fields
                Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show()
                name.editText?.text?.clear()
                number.editText?.text?.clear()
                email.editText?.text?.clear()
                description.editText?.text?.clear()
                typee.setSelection(0)


            }.addOnFailureListener{err->
                // Show an error message if the data insertion fails
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

        // Start the FetchingName activity
        val intent = Intent(this, FetchingName::class.java)
        startActivity(intent)
    }

}