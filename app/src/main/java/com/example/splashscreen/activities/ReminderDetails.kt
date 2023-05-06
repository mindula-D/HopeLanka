package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.splashscreen.R
import com.example.splashscreen.models.ReminderModel
import com.google.firebase.database.FirebaseDatabase

class ReminderDetails : AppCompatActivity() {

    private lateinit var tvRemID: TextView
    private lateinit var tvRemName: TextView
    private lateinit var tvRemDate: TextView
    private lateinit var tvRemTime: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var builder: AlertDialog.Builder

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_details)

        btnDelete = findViewById(R.id.btnDelete)
        builder = AlertDialog.Builder(this)

        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }

        val goBackText = findViewById<TextView>(R.id.goBackToReminders)
        goBackText.setOnClickListener {
            val intent = Intent(this,MyReminders::class.java)
            startActivity(intent)
        }

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("remID").toString(),
                intent.getStringExtra("remName").toString()
            )
        }

        btnDelete.setOnClickListener {
            builder.setTitle("Alert!")
                .setMessage("Confirm Delete!")
                .setCancelable(true)
                .setPositiveButton("Yes"){dialogInterface,it ->
                    deleteRecord(
                        intent.getStringExtra("remID").toString()
                    )
                    finish()
                }
                .setNegativeButton("No"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
                .show()
        }

    }

    private fun deleteRecord(
        id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Reminders").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Reminder Deleted Successfully", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MyReminders::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvRemID = findViewById(R.id.tvRemID)
        tvRemName = findViewById(R.id.tvRemName)
        tvRemDate = findViewById(R.id.tvRemDate)
        tvRemTime = findViewById(R.id.tvRemTime)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvRemID.text = intent.getStringExtra("remID")
        tvRemName.text = intent.getStringExtra("remName")
        tvRemDate.text = intent.getStringExtra("remDate")
        tvRemTime.text = intent.getStringExtra("remTime")

    }

    private fun openUpdateDialog(
        remID: String,
        remName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog,null)

        mDialog.setView(mDialogView)

        val etRemName = mDialogView.findViewById<EditText>(R.id.etRemName)
        val etRemDate = mDialogView.findViewById<EditText>(R.id.etRemDate)
        val etRemTime = mDialogView.findViewById<EditText>(R.id.etRemTime)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etRemName.setText(intent.getStringExtra("remName").toString())
        etRemDate.setText(intent.getStringExtra("remDate").toString())
        etRemTime.setText(intent.getStringExtra("remTime").toString())

        mDialog.setTitle("Updating Reminder")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateRemData(
                remID,
                etRemName.text.toString(),
                etRemDate.text.toString(),
                etRemTime.text.toString()
            )

            Toast.makeText(applicationContext, "Reminder Details Updated", Toast.LENGTH_LONG).show()

            //Setting updated data to our textviews
            tvRemName.text = etRemName.text.toString()
            tvRemDate.text = etRemDate.text.toString()
            tvRemTime.text = etRemTime.text.toString()

            alertDialog.dismiss()

        }

    }

    private fun updateRemData(
        id: String,
        name: String,
        date: String,
        time: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Reminders").child(id)
        val empInfo = ReminderModel(id, name, date, time)
        dbRef.setValue(empInfo)
    }

}