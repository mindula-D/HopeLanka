package com.example.text.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.text.R
import com.example.text.models.DonationModel
import com.google.firebase.database.FirebaseDatabase

class ReadyToDonate : AppCompatActivity() {
    private lateinit var tvdonationId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvNumber: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvType: TextView
    private lateinit var tvDesc: TextView
    private lateinit var builder: AlertDialog.Builder
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ready_to_donate)
        btnDelete = findViewById(R.id.btnDelete)
        builder = AlertDialog.Builder(this)

        // initialize views
        initView()

        // set data to views
        setValuesToViews()


        // initialize back arrow button and set click listener
        val backArrow = findViewById<Button>(R.id.buttonArrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, FetchingName::class.java)
            startActivity(intent)}

        // initialize donation done button and set click listener
        val donationdone = findViewById<Button>(R.id.donateNow)
        donationdone.setOnClickListener {
            val intent = Intent(this, DonationSuccess::class.java)
            startActivity(intent) }

        //add a click listener to edit button
        btnUpdate.setOnClickListener {
            // open update dialog with existing data
            openUpdateDialog(
                intent.getStringExtra("DonationId").toString(),
                intent.getStringExtra("name").toString()
//                intent.getStringExtra("number").toString(),
//                intent.getStringExtra("email").toString(),
//                intent.getStringExtra("type").toString(),
//                intent.getStringExtra("description").toString()

            )
        }

        // add click listener to delete button
        btnDelete.setOnClickListener {
            // create alert dialog to confirm deletion
            builder.setTitle("Alert!")
                .setMessage("Do you want to delete the form")
                .setCancelable(true)
                .setPositiveButton("Yes"){dialogInterface,it ->
                    // delete record and finish activity
                    deleteRecord(
                        intent.getStringExtra("DonationId").toString())
                    finish()
                }
                .setNegativeButton("No"){dialogInterface,it ->
                    // dismiss dialog
                    dialogInterface.cancel()
                }
                .show()
        }


        }

    // initialize views
    private fun initView() {
        tvdonationId = findViewById(R.id.donationID)
        tvName = findViewById(R.id.donorName)
        tvNumber = findViewById(R.id.donorContact)
        tvEmail = findViewById(R.id.donorEmail)
        tvType = findViewById(R.id.itemType)
        tvDesc = findViewById(R.id.donorDesc)

        // initialize update and delete buttons
        btnUpdate = findViewById(R.id.btnEdit)
        btnDelete = findViewById(R.id.btnDelete)
    }

    // set data to views
    private fun setValuesToViews() {
        tvdonationId.text = intent.getStringExtra("DonationId")
        tvName.text = intent.getStringExtra("name")
        tvNumber.text = intent.getStringExtra("number")
        tvEmail.text = intent.getStringExtra("email")
        tvType.text = intent.getStringExtra("type")
        tvDesc.text = intent.getStringExtra("description")

    }
    //delete function
    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("ItemDonation").child(id)
        val iTask = dbRef.removeValue()

        iTask.addOnSuccessListener {
            // show toast message on success
            Toast.makeText(this, "Form is deleted", Toast.LENGTH_LONG).show()
            // navigate to delete warning donation activity
            val intent = Intent(this, DeleteWarningDonation::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            // show toast message on failure
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
    private fun openUpdateDialog(
        DonationId: String,  // id of the donation item to be updated
        name: String        // name of the donation item to be updated
    ) {
        // create an AlertDialog to display the update dialog
        val iDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val iDialogView = inflater.inflate(R.layout.item_update_dialog, null)

        iDialog.setView(iDialogView)

        // get references to the EditText views in the update dialog
        val Name = iDialogView.findViewById<EditText>(R.id.Name)
        val Number = iDialogView.findViewById<EditText>(R.id.donornumber)
        val Email = iDialogView.findViewById<EditText>(R.id.donoremail)
        val Type = iDialogView.findViewById<EditText>(R.id.type)
        val Decription = iDialogView.findViewById<EditText>(R.id.description)
        val updatebutton = iDialogView.findViewById<Button>(R.id.btnUpdate)

        // set the text of the EditText views to the current values of the item to be updated
        Name.setText(intent.getStringExtra("name").toString())
        Number.setText(intent.getStringExtra("number").toString())
        Email.setText(intent.getStringExtra("email").toString())
        Type.setText(intent.getStringExtra("type").toString())
        Decription.setText(intent.getStringExtra("description").toString())

        //setting the dialog title
        iDialog.setTitle("Updating $name Record")

        // create and display the update dialog
        val alertDialogg = iDialog.create()
        alertDialogg.show()

        // set an OnClickListener for the "Update" button in the dialog
        updatebutton.setOnClickListener {
            // call the updateItemData function with the new values entered in the dialog
            updateItemData(
                DonationId,
                Name.text.toString(),
                Number.text.toString(),
                Email.text.toString(),
                Type.text.toString(),
                Decription.text.toString()
            )

            // display a Toast message to indicate that the item was updated successfully
            Toast.makeText(applicationContext, "Item Form Updated", Toast.LENGTH_LONG).show()

            // set the text of the TextView views in the main activity to the updated values
            tvName.text =   Name.text.toString()
            tvNumber.text = Number.text.toString()
            tvEmail.text =  Email.text.toString()
            tvType.text =   Type.text.toString()
            tvDesc.text =   Decription.text.toString()

            // dismiss the update dialog
            alertDialogg.dismiss()
        }

    }
    // function to update the item data in the Firebase Realtime Database
    private fun updateItemData(
        id: String,
        name: String,
        number: String,
        email: String,
        type:String,
        description:String
    ) {
        // get a reference to the item to be updated in the Firebase Realtime Database
        val dbRef = FirebaseDatabase.getInstance().getReference("ItemDonation").child(id)
        // create a DonationModel object with the updated values
        val itemInfo = DonationModel(id,name,number,email,type,description)
        // update the data for the item in the Firebase Realtime Database
        dbRef.setValue(itemInfo)

    }



    }
