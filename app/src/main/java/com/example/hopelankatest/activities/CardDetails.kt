package com.example.hopelankatest.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.hopelankatest.R
import com.example.hopelankatest.models.CardModel
import com.google.firebase.database.FirebaseDatabase

class CardDetails : AppCompatActivity() {
    // Declare variables for the views and buttons in the layout
    private lateinit var tvCardID: TextView
    private lateinit var tvBankNameDetail: TextView
    private lateinit var tvCardNumberDetail: TextView
    private lateinit var tvMonthDetail: TextView
    private lateinit var tvYearDetail: TextView
    private lateinit var tvCVVDetail: TextView
    private lateinit var tvHolderName: TextView
    private lateinit var tvCardType: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        // find the delete button view
        btnDelete = findViewById(R.id.btnDelete)
        // create an AlertDialog builder
        builder = AlertDialog.Builder(this)

        // Set a click listener for the back button
        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener{
            val intent = Intent(this, PreviouslyAddedCards::class.java)
            startActivity(intent)
        }

        // Set a click listener for the select card button
        val btnSelectCard = findViewById<Button>(R.id.btnSelectCard)
        btnSelectCard.setOnClickListener{
            val intent = Intent(this, EnterAmount::class.java)
            startActivity(intent)
        }

        // initialize all the views
        initView()
        // set the values of all the views
        setValuesToViews()

        // set a click listener on the update button
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cardId").toString(),
                intent.getStringExtra("bankName").toString()
            )
        }

        // set a click listener on the delete button
        btnDelete.setOnClickListener {
            // show an alert dialog
            builder.setTitle("Alert!")
                .setMessage("Do you want to delete card details?")
                .setCancelable(true)
                .setPositiveButton("Yes"){dialogInterface,it ->
                    // delete the card details
                    deleteRecord(
                        intent.getStringExtra("cardId").toString()
                    )
                    finish()
                }
                .setNegativeButton("No"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .show()
        }
    }

    // function to delete a record
    private fun deleteRecord(
        id: String
    ){
        // get a reference to the CardDetails node in the database
        val dbRef = FirebaseDatabase.getInstance().getReference("CardDetails").child(id)
        // remove the value at this node
        val cTask = dbRef.removeValue()

        // add a success listener
        cTask.addOnSuccessListener {
            Toast.makeText(this, "Card Details are deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, PreviouslyAddedCards::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    // function of initializing all the views
    private fun initView() {
        tvCardID = findViewById(R.id.tvCardID)
        tvBankNameDetail = findViewById(R.id.tvBankNameDetail)
        tvCardNumberDetail = findViewById(R.id.tvCardNumberDetail)
        tvMonthDetail = findViewById(R.id.tvMonthDetail)
        tvYearDetail = findViewById(R.id.tvYearDetail)
        tvCVVDetail = findViewById(R.id.tvCVVDetail)
        tvHolderName= findViewById(R.id.tvHolderName)
        tvCardType = findViewById(R.id.tvCardType)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    //function of setting values to the view objects
    private fun setValuesToViews() {
        tvCardID.text = intent.getStringExtra("cardId")
        tvBankNameDetail.text = intent.getStringExtra("bankName")
        tvCardNumberDetail.text = intent.getStringExtra("cardNumber")
        tvMonthDetail.text = intent.getStringExtra("month")
        tvYearDetail.text = intent.getStringExtra("year")
        tvCVVDetail.text = intent.getStringExtra("cvv")
        tvHolderName.text = intent.getStringExtra("cardHolderName")
        tvCardType.text = intent.getStringExtra("cardType")
    }

    //function of opening the update dialog with pre filled values.
    private fun openUpdateDialog(
        cardId: String,
        bankName: String) {

        // Create an AlertDialog object
        val cDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val cDialogView = inflater.inflate(R.layout.activity_update_card, null)
        cDialog.setView(cDialogView)

        // Get references to all the EditText and Button objects
        val etBankName = cDialogView.findViewById<EditText>(R.id.UpdateName)
        val etCardNumber = cDialogView.findViewById<EditText>(R.id.card_num)
        val etMonth = cDialogView.findViewById<EditText>(R.id.month)
        val etYear = cDialogView.findViewById<EditText>(R.id.year)
        val etCVV = cDialogView.findViewById<EditText>(R.id.updatecvv)
        val etCardHolderName = cDialogView.findViewById<EditText>(R.id.Name)
        val etCardType = cDialogView.findViewById<EditText>(R.id.updateCardType)
        val btnUpdateCard = cDialogView.findViewById<Button>(R.id.btnUpdate)

        // Set the values of the fields
        etBankName.setText(intent.getStringExtra("bankName").toString())
        etCardNumber.setText(intent.getStringExtra("cardNumber").toString())
        etMonth.setText(intent.getStringExtra("month").toString())
        etYear.setText(intent.getStringExtra("year").toString())
        etCVV.setText(intent.getStringExtra("cvv").toString())
        etCardHolderName.setText(intent.getStringExtra("cardHolderName").toString())
        etCardType.setText(intent.getStringExtra("cardType").toString())

        // Create and show the AlertDialog.
        val alertDialog = cDialog.create()
        alertDialog.show()

        // Set a click listener for the update button
        btnUpdateCard.setOnClickListener{
            updateCardDetails(
                cardId,
                etBankName.text.toString(),
                etCardNumber.text.toString(),
                etMonth.text.toString(),
                etYear.text.toString(),
                etCVV.text.toString(),
                etCardHolderName.text.toString(),
                etCardType.text.toString()
            )

            Toast.makeText(applicationContext, "Card Details Updated", Toast.LENGTH_LONG).show()

            tvBankNameDetail.text = etBankName.text.toString()
            tvCardNumberDetail.text = etCardNumber.text.toString()
            tvMonthDetail.text = etMonth.text.toString()
            tvYearDetail.text = etYear.text.toString()
            tvCVVDetail.text = etCVV.text.toString()
            tvHolderName.text = etCardHolderName.text.toString()
            tvCardType.text = etCardType.text.toString()

            alertDialog.dismiss()
        }
    }

    //function of updating cad details
    private fun updateCardDetails(
        id: String,
        bankName: String,
        cardNumber: String,
        month: String,
        year: String,
        cvv: String,
        cardHolderName: String,
        type : String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("CardDetails").child(id)
        val cardInfo = CardModel(id, cardNumber, month, year, cvv, cardHolderName, bankName,type)
        dbRef.setValue(cardInfo)
    }

}