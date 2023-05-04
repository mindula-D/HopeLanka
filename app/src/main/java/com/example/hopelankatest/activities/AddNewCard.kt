
package com.example.hopelankatest.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.hopelankatest.models.CardModel
import com.example.hopelankatest.R
import com.example.hopelankatest.activities.CardSavedSuccessfully
import com.example.hopelankatest.activities.PaymentOptions
import com.example.hopelankatest.activities.PreviouslyAddedCards
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewCard : AppCompatActivity() {
    // Declare variables for the fields in the layout
    private lateinit var etCardNumber: TextInputLayout
    private lateinit var etMonth: TextInputLayout
    private lateinit var etYear: TextInputLayout
    private lateinit var etCvv: TextInputLayout
    private lateinit var etCardHolderName: TextInputLayout
    private lateinit var etBankName: TextInputLayout
    private lateinit var etCardType: RadioGroup
    private lateinit var saveCard: Button

    // Declare a variable for the database reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        // Assign the views to the corresponding variables using their id
        etCardNumber = findViewById(R.id.cardNumber)
        etMonth = findViewById(R.id.month)
        etYear = findViewById(R.id.year)
        etCvv = findViewById(R.id.cvv)
        etCardHolderName = findViewById(R.id.cardHolderName)
        etBankName = findViewById(R.id.bankName)
        saveCard = findViewById(R.id.saveCard)
        etCardType = findViewById(R.id.cardType)

        // Get a reference to the "CardDetails" node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("CardDetails")

        // Set a click listener for the saveCard button
        saveCard.setOnClickListener {
            saveCardDetails()
        }

        // Set a click listener for the back button
        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener {
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }
    }

    // Function to save the card details
    private fun saveCardDetails() {

        // Get the values from layout
        val cardNumber = etCardNumber.editText?.text.toString()
        val month = etMonth.editText?.text.toString()
        val year = etYear.editText?.text.toString()
        val cvv = etCvv.editText?.text.toString()
        val cardHolderName = etCardHolderName.editText?.text.toString()
        val bankName = etBankName.editText?.text.toString()
        val cardTypeRadioGroup = findViewById<RadioGroup>(R.id.cardType)
        val cardType = when (cardTypeRadioGroup.checkedRadioButtonId) {
            R.id.master -> "master"
            R.id.visa -> "visa"
            else -> ""
        }

        // Check if any of the fields are empty
        if (cardNumber.isEmpty() || month.isEmpty() || year.isEmpty() || cvv.isEmpty() || cardHolderName.isEmpty() || bankName.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if any of the month is greater than 12
        if(month > 12.toString()){
            Toast.makeText(this, "Please enter valid month", Toast.LENGTH_SHORT).show()
            return
        }

        //unique ID for the new card
        val cardId = dbRef.push().key!!

        // Create a new card object
        val card = CardModel(cardId, cardNumber, month, year, cvv, cardHolderName, bankName, cardType)

        // Save the new card object to the database
        dbRef.child(cardId).setValue(card)
            .addOnCompleteListener {
                etCardNumber.editText?.text?.clear()
                etMonth.editText?.text?.clear()
                etYear.editText?.text?.clear()
                etCvv.editText?.text?.clear()
                etCardHolderName.editText?.text?.clear()
                etBankName.editText?.text?.clear()
                cardTypeRadioGroup.clearCheck()

                // Launch the activity
                val intent = Intent(this, CardSavedSuccessfully::class.java)
                startActivity(intent)
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
    }

}
