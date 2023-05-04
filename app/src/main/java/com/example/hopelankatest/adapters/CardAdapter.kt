package com.example.hopelankatest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelankatest.R
import com.example.hopelankatest.models.CardModel

class CardAdapter(private val cardList: ArrayList<CardModel>) : RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    private lateinit var mListner : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener : onItemClickListener){
        mListner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cardList[position]
        holder.tvCardNumber.text = currentCard.cardNumber
        holder.tvBankName.text = currentCard.bankName
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvCardNumber : TextView = itemView.findViewById(R.id.tvCardNumber)
        val tvBankName : TextView = itemView.findViewById(R.id.tvBankName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
      }
}