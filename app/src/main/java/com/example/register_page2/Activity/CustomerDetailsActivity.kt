// update and delete code
// Importing libraries
package com.example.register_page2.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.register_page2.Model.CustomerModel
import com.example.register_page2.R
import com.google.firebase.database.FirebaseDatabase

class CustomerDetailsActivity:AppCompatActivity() {
    // Declaring the views and buttons
    private lateinit var tvCusId :TextView
    private lateinit var tvCusName :TextView
    private lateinit var tvCusEmail:TextView
    private lateinit var tvCusAddress:TextView
    private lateinit var tvCusContact:TextView
    private lateinit var tvCusBio :TextView

    private lateinit var update_Edit_btn:Button
    private lateinit var Delete_view_btn: Button
    private lateinit var builder: AlertDialog.Builder

    // This method gets called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_client_profile)
        initView()
        setValuesToViews()
        Delete_view_btn = findViewById(R.id.Delete_view_btn)
        builder = AlertDialog.Builder(this)

        // Set up the click listener for the update button
        update_Edit_btn.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("customerId").toString(),
                intent.getStringExtra("input_Name_pro").toString()

            )

        }
        // Set up the click listener for the delete button
        Delete_view_btn.setOnClickListener {
            // Show a confirmation dialog before deleting the record
            builder.setTitle("Alert!")
                .setMessage("Do you want to delete profile details?")
                .setCancelable(true)
                .setPositiveButton("Yes"){dialogInterface,it ->
                    deleteRecord(
                        intent.getStringExtra("customerId").toString()
                    )
                    finish()
                }
                .setNegativeButton("No"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .show()
        }

    }
    // This method initializes the views
    private fun initView(){
        tvCusId = findViewById(R.id.cusid)
        tvCusName = findViewById(R.id.Update_Name1)
        tvCusEmail = findViewById(R.id.Update_Email1)
        tvCusAddress = findViewById(R.id.Update_Address)
        tvCusContact = findViewById(R.id.Update_ContactNum)
        tvCusBio = findViewById(R.id.Update_Bio)

        update_Edit_btn= findViewById(R.id.update_Edit_btn)
        Delete_view_btn = findViewById(R.id.Delete_view_btn)
    }
    // This method sets the values of the views from the intent extras
    private fun setValuesToViews(){

        tvCusId.text =  intent.getStringExtra("customerId")
        tvCusName.text = intent.getStringExtra("input_Name_pro")
        tvCusEmail.text= intent.getStringExtra("input_Email_pro")
        tvCusAddress.text= intent.getStringExtra( "input_Address_pro")
        tvCusContact.text= intent.getStringExtra( "input_Number")
        tvCusBio.text= intent.getStringExtra( "input_bio_pro")


    }

    //  method deletes the record from the database
    private fun deleteRecord(
        customerId: String
    ){

        val dbRef = FirebaseDatabase.getInstance().getReference("customer").child( customerId)
        val cTask = dbRef.removeValue()

        cTask.addOnSuccessListener {
            Toast.makeText(this, "Customer details are deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }


    }

    private fun openUpdateDialog(
        customerId:String,
        input_Name_pro:String
    ){
        // Create a new dialog
        val sDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val sDialogView = inflater.inflate(R.layout.update_dialog,null)

        sDialog.setView(sDialogView)

        val uName = sDialogView.findViewById<EditText>(R.id.update_Name_pro)
        val uEmail = sDialogView.findViewById<EditText>(R.id.update_Email_pro)
        val uAddress = sDialogView.findViewById<EditText>(R.id.update_Address_pro)
        val uContact = sDialogView.findViewById<EditText>(R.id.update_Number)
        val uBio = sDialogView.findViewById<EditText>(R.id.update_bio_pro)
        val updatee =sDialogView.findViewById<Button>(R.id.updateButton)

        // Set the edit text fields to the current values
        uName.setText(tvCusName.text)
        uEmail.setText(tvCusEmail.text)
        uAddress.setText(tvCusAddress.text)
        uContact.setText(tvCusContact.text)
        uBio.setText(tvCusBio.text)

        sDialog.setTitle("UPDATING $input_Name_pro RECORDS")
        val alertDialog = sDialog.create()
        alertDialog.show()

        // Call the updateCusData function with the updated information
        updatee.setOnClickListener{
            updateCusData(
                customerId,
                uName.text.toString(),
                uEmail.text.toString(),
                uAddress.text.toString(),
                uContact.text.toString(),
                uBio.text.toString(),
                alertDialog
            )

        }
    }

    //  updates the customer information in Firebase
    private fun updateCusData(
        id:String,
        name:String,
        email:String,
        address:String,
        number:String,
        bio:String,
        alertDialog: AlertDialog
    ){

        val dbRef = FirebaseDatabase.getInstance().getReference("customer").child(id)
        val customerInfo = CustomerModel(id,name,email ,address, number, bio)
        dbRef.setValue(customerInfo).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Update the text views with the new values
                tvCusName.text =  name
                tvCusEmail.text=  email
                tvCusAddress.text= address
                tvCusContact.text=  number
                tvCusBio.text= bio

                Toast.makeText(applicationContext,"Customer details are updated",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Updating failed",Toast.LENGTH_LONG).show()
            }
            alertDialog.dismiss()
        }

    }}