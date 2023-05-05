package com.example.hopelankatest.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelankatest.R
import com.example.hopelankatest.adapters.CardAdapter
import com.example.hopelankatest.models.CardModel
import com.google.firebase.database.*

class PreviouslyAddedCards : AppCompatActivity() {
    // Declare variables for the fields in the layout
    private lateinit var cardRecyclerView: RecyclerView
    private lateinit var cardList: ArrayList<CardModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previously_added_cards)

        // Set a click listener for the back button
        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener {
            val intent = Intent(this, EnterAmount::class.java)
            startActivity(intent)
        }

        // Set a click listener for the AddNewCard button
        val btnAddNewCard = findViewById<Button>(R.id.btnAddNewCard)
        btnAddNewCard.setOnClickListener {
            val intent = Intent(this, AddNewCard::class.java)
            startActivity(intent)
        }

        // Assign the views to the corresponding variables using their id
        cardRecyclerView = findViewById(R.id.rvCards)
        cardRecyclerView.layoutManager = LinearLayoutManager(this)
        cardRecyclerView.setHasFixedSize(true)

        cardList = arrayListOf<CardModel>()

        //call method to get card details
        getCardDetails()
    }

    private fun getCardDetails() {
        //set recycler view visibility to GONE
        cardRecyclerView.visibility = View.GONE

        //get reference to the 'CardDetails' node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("CardDetails")
        // Attach a value event listener
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                if (snapshot.exists()) {
                    //iterate through each child node and get the CardModel object
                    for (cardSnap in snapshot.children) {
                        val cardData = cardSnap.getValue(CardModel::class.java)
                        cardList.add(cardData!!)
                    }
                    //create and set the adapter for the recycler view
                    val mAdapter = CardAdapter(cardList)
                    cardRecyclerView.adapter = mAdapter

                    //set click listener for the items in the recycler view
                    mAdapter.setOnItemClickListener(object : CardAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@PreviouslyAddedCards, CardDetails::class.java)

                            //put extras
                            intent.putExtra("cardId", cardList[position].cardId)
                            intent.putExtra("bankName", cardList[position].bankName)
                            intent.putExtra("cardNumber", cardList[position].cardNumber)
                            intent.putExtra("month", cardList[position].month)
                            intent.putExtra("year", cardList[position].year)
                            intent.putExtra("cvv", cardList[position].cvv)
                            intent.putExtra("cardHolderName", cardList[position].cardHolderName)
                            intent.putExtra("cardType", cardList[position].cardType)
                            startActivity(intent)
                        }
                    })

                    //set recycler view visibility to VISIBLE
                    cardRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}