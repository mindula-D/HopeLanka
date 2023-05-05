package com.example.register_page2.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.register_page2.R
import com.example.register_page2.Adapter.CustomerAdapter
import com.example.register_page2.Model.CustomerModel
import com.google.firebase.database.*

class FetchingActivity :AppCompatActivity() {
    // data fetching code
    // Initialize variables

    private lateinit var customerRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var customerList:ArrayList<CustomerModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)
        // Find the views and set up the recycler view
        customerRecyclerView= findViewById(R.id.rvCustomer)
        customerRecyclerView.layoutManager = LinearLayoutManager( this)
        customerRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        customerList= arrayListOf<CustomerModel> ()

        getCustomerData()
    }
    // Function to retrieve customer data from Firebase
    private  fun getCustomerData(){
        customerRecyclerView.visibility = View.GONE
        tvLoadingData.visibility-View.VISIBLE

        // Get a reference to the "customer" node in the Firebase database
        dbRef = FirebaseDatabase.getInstance().getReference("customer")

        dbRef.addValueEventListener((object :ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                customerList.clear()
                if (snapshot.exists()){

                    for(customerSnap in snapshot.children){


                        val customerData = customerSnap.getValue(CustomerModel::class.java)
                        customerList.add(customerData!!)
                    }

                    val sAdapter= CustomerAdapter(customerList)
                    customerRecyclerView.adapter =sAdapter


                    // Set an item click listener for the CustomerAdapter
                    sAdapter.setOnItemClickListener(object : CustomerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity, CustomerDetailsActivity::class.java)
                            //put extras
                            intent.putExtra("customerId",customerList[position].customerId )
                            intent.putExtra("input_Name_pro",customerList[position].input_Name_pro )
                            intent.putExtra("input_Email_pro",customerList[position].input_Email_pro )
                            intent.putExtra("input_Address_pro",customerList[position].input_Address_pro )
                            intent.putExtra("input_Number",customerList[position].input_Number )
                            intent.putExtra("input_bio_pro",customerList[position].input_bio_pro )
                             startActivity(intent)
                        }


                    })

                    customerRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility=View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }))


    }

}