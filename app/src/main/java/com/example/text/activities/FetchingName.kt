package com.example.text.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.text.models.DonationModel
import com.example.text.R
import com.example.text.adapters.ItemAdapter
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class FetchingName : AppCompatActivity() {

    //initialize the necessary variables
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var searchView: SearchView
    private lateinit var itemList: ArrayList<DonationModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching_name)

        //initialize recycle view
        itemRecyclerView = findViewById(R.id.rvItem)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchView =  findViewById(R.id.searchView)

        //set up the search functionality
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        //initialize the array list
        itemList = arrayListOf<DonationModel>()

        //call the method to get the data from the database
        getCustomerData()
    }

    //method to filter the list based on the search query
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<DonationModel>()
            for (i in itemList) {
                if (i.Name?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    //method to get the data from the database
    private fun getCustomerData() {
        itemRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        //refferencing to the database
        dbRef = FirebaseDatabase.getInstance().getReference("ItemDonation")

        dbRef.addValueEventListener(object : ValueEventListener {
            //getting list of customers
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                if (snapshot.exists()){
                    for (itemSnap in snapshot.children){
                        val itemData = itemSnap.getValue(DonationModel::class.java)
                        itemList.add(itemData!!)
                    }
                    // Create a new instance of ItemAdapter and pass the itemList and a listener to handle clicks on each item
                    adapter = ItemAdapter(itemList, object: ItemAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            // Create a new Intent to navigate to ReadyToDonate activity
                            val intent = Intent(this@FetchingName, ReadyToDonate::class.java)

                            // Put extras in the intent to pass data to the ReadyToDonate activity
                            intent.putExtra("DonationId", itemList[position].donationId)
                            intent.putExtra("name", itemList[position].Name)
                            intent.putExtra("number", itemList[position].Number)
                            intent.putExtra("email", itemList[position].Email)
                            intent.putExtra("type", itemList[position].Typee)
                            intent.putExtra("description", itemList[position].Description)

                            // Start the ReadyToDonate activity with the created intent
                            startActivity(intent)
                        }
                    })
                    // Set the adapter to the RecyclerView
                    itemRecyclerView.adapter = adapter

                    // Make the RecyclerView visible and hide the loading text view
                    itemRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FetchingName", "Database error: ${error.message}")
            }
        })
    }
}


