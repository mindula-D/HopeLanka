package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.splashscreen.R
import com.example.splashscreen.models.ReminderModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//Create CRUD Operations
//Storing data in Firebase Realtime Database
//Validaations
class RemindManager : AppCompatActivity() {

    private lateinit var reminderName: EditText
    private lateinit var reminderDate: EditText
    private lateinit var reminderTime: EditText
    private lateinit var btnAddReminder: Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remind_manager)

        reminderName = findViewById(R.id.textInputEditTextReminder)
        reminderDate = findViewById(R.id.textInputEditDate)
        reminderTime = findViewById(R.id.textInputEditTextTime)
        btnAddReminder = findViewById(R.id.addNewReminderButton)

        dbRef = FirebaseDatabase.getInstance().getReference("Reminders")

        btnAddReminder.setOnClickListener {
            saveReminders()
        }


        //Navigate to My Reminders
        val myRemindersButton = findViewById<Button>(R.id.seeRemindersButton)
        myRemindersButton.setOnClickListener {
            val Intent = Intent(this, MyReminders::class.java)
            startActivity(Intent)
        }

        //Navigate to Overview Tab
        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

        //Navigate to Home Tab
        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }
    }

    private fun saveReminders() {

        //Getting Values
        val newRemName = reminderName.text.toString()
        val newRemDate = reminderDate.text.toString()
        val newRemTime = reminderTime.text.toString()

        if (newRemName.isEmpty()){
            reminderName.error = "Please Enter a Reminder"
        }
        if (newRemDate.isEmpty()){
            reminderDate.error = "Please Enter a Date"
        }
        if (newRemTime.isEmpty()){
            reminderTime.error = "Please Enter a Time"
        }

        val remID = dbRef.push().key!!

        val reminder = ReminderModel(remID, newRemName, newRemDate, newRemTime)

        dbRef.child(remID).setValue(reminder)
            .addOnCompleteListener{
                Toast.makeText(this, "Reminder Added Successfully", Toast.LENGTH_LONG).show()

                //Clearing inserted data after saving to DB
                reminderName.text.clear()
                reminderDate.text.clear()
                reminderTime.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this, "Error in Adding Reminder ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

}