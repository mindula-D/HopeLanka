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


// Adapter class for the RecyclerView
class ItemAdapter(private val itemList: ArrayList<DonationModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Initialize the filtered list to be the same as the original list
    private var filteredList: ArrayList<DonationModel> = itemList

    // Called when the RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Inflate the layout for the list item and return a new ViewHolder for it
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    // Called when the RecyclerView needs to display data in a ViewHolder
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Get the data at the current position in the filtered list
        val currentItem = filteredList[position]

        // Set the text of the TextView in the ViewHolder to the name of the current item
        holder.tvName.text = currentItem.Name
//        holder.tvNumber.text = currentItem.Number
//        holder.tvEmail.text = currentItem.Email
//        holder.tvType.text = currentItem.Typee
//        holder.tvDescription.text = currentItem.Description

        // Set an OnClickListener on the ViewHolder's itemView that calls onItemClick in the listener
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    // Return the number of items in the filtered list
    override fun getItemCount() = filteredList.size

    // Set the filtered list to a new list and notify the adapter that the data has changed
    fun setFilteredList(filteredList: ArrayList<DonationModel>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }

    // Interface for listening to item click events
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // ViewHolder class for the RecyclerView
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.CusName)
//        val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
//        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
//        val tvType: TextView = itemView.findViewById(R.id.tvType)
//        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }
}

