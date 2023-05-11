package com.example.splashscreen.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splashscreen.R
import com.example.splashscreen.adapters.RemAdapter
import com.example.splashscreen.models.ReminderModel
import com.google.firebase.database.*


//Retrieve and Insert CRUD Operations / Mainly Retrieve
class MyReminders : AppCompatActivity() {

    private lateinit var remRecyclerView: RecyclerView
    private lateinit var tvLoadingDat: TextView
    private lateinit var remList: ArrayList<ReminderModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_reminders)

        remRecyclerView = findViewById(R.id.rvReminders)
        remRecyclerView.layoutManager = LinearLayoutManager(this)
        remRecyclerView.setHasFixedSize(true)
        tvLoadingDat = findViewById(R.id.tvLoadingData)

        remList = arrayListOf<ReminderModel>()

        getReminderData()


        //Navigate to Remind Manager
        val remindersButton = findViewById<Button>(R.id.goToRemindManagerButton)
        remindersButton.setOnClickListener {
            val Activity = Intent(this, RemindManager::class.java)
            startActivity(Activity)
        }

        //Navigate to Overview tab
        val overviewTab = findViewById<ImageView>(R.id.overview)
        overviewTab.setOnClickListener {
            val Intent = Intent(this, OverviewStatistics::class.java)
            startActivity(Intent)
        }

        //Navigate to Home tab
        val homeTab = findViewById<ImageView>(R.id.home)
        homeTab.setOnClickListener {
            val Intent = Intent(this, HomeFeed::class.java)
            startActivity(Intent)
        }
    }

    private fun getReminderData(){
        remRecyclerView.visibility = View.GONE
        tvLoadingDat.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Reminders")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                remList.clear()
                if (snapshot.exists()){
                    for (remSnap in snapshot.children){
                        val remData = remSnap.getValue(ReminderModel::class.java)
                        remList.add(remData!!)
                    }
                    val rmAdapter = RemAdapter(remList)
                    remRecyclerView.adapter = rmAdapter

                    rmAdapter.setOnItemClickListener(object : RemAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MyReminders, ReminderDetails::class.java)

                            //Put extra data
                            intent.putExtra("remID" , remList[position].remID)
                            intent.putExtra("remName" , remList[position].newRemName)
                            intent.putExtra("remDate" , remList[position].newRemDate)
                            intent.putExtra("remTime" , remList[position].newRemTime)
                            startActivity(intent)
                        }

                    })

                    remRecyclerView.visibility = View.VISIBLE
                    tvLoadingDat.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}