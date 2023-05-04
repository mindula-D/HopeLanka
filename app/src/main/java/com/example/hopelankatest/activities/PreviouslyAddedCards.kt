package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelankatest.R
import com.example.hopelankatest.adapters.CardAdapter
import com.example.hopelankatest.models.CardModel
import com.example.hopelankatest.models.PaymentModel
import com.google.firebase.database.*

class PreviouslyAddedCards : AppCompatActivity() {

    private lateinit var cardRecyclerView : RecyclerView
    private lateinit var cardList : ArrayList<CardModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previously_added_cards)

        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener{
            val intent = Intent(this, EnterAmount::class.java)
            startActivity(intent)
        }

        val btnAddNewCard = findViewById<Button>(R.id.btnAddNewCard)
        btnAddNewCard.setOnClickListener{
            val intent = Intent(this, AddNewCard::class.java)
            startActivity(intent)
        }

        cardRecyclerView = findViewById(R.id.rvCards)
        cardRecyclerView.layoutManager = LinearLayoutManager(this)
        cardRecyclerView.setHasFixedSize(true)

        cardList = arrayListOf<CardModel>()

        getCardDetails()
    }

    private fun getCardDetails(){
        cardRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("CardDetails")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                if(snapshot.exists()){
                    for(cardSnap in snapshot.children){
                        val cardData = cardSnap.getValue(CardModel::class.java)
                        cardList.add(cardData!!)
                    }
                    val mAdapter = CardAdapter(cardList)
                    cardRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : CardAdapter.onItemClickListener{
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

                    cardRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
//        val amount = intent.getStringExtra("amount")
//        Toast.makeText(this, amount, Toast.LENGTH_LONG).show()
//        val intent2 = Intent(this, CardDetails::class.java).also {
//            it.putExtra("amount", amount)
//            startActivity(it)
//        }
//        startActivity(intent2)

    }
}