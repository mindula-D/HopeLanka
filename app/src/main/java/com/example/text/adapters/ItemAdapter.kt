package com.example.text.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.text.R
import com.example.text.models.DonationModel
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val itemList: ArrayList<DonationModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var filteredList: ArrayList<DonationModel> = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = filteredList[position]

        holder.tvName.text = currentItem.Name
//        holder.tvNumber.text = currentItem.Number
//        holder.tvEmail.text = currentItem.Email
//        holder.tvType.text = currentItem.Typee
//        holder.tvDescription.text = currentItem.Description

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount() = filteredList.size

    fun setFilteredList(filteredList: ArrayList<DonationModel>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.CusName)
//        val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
//        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
//        val tvType: TextView = itemView.findViewById(R.id.tvType)
//        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }
}

