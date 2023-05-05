package com.example.register_page2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.register_page2.R
import com.example.register_page2.Model.CustomerModel


class CustomerAdapter(private val customerList:ArrayList<CustomerModel>): RecyclerView.Adapter<CustomerAdapter.ViewHolder>(){

    private lateinit var cListener: onItemClickListener



    interface onItemClickListener{
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
         cListener=clickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val custView = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item,parent,false)
        return ViewHolder(custView,cListener)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCustomer = customerList[position]
        holder.tvProfile.text = currentCustomer.input_Name_pro
    }



    override  fun getItemCount(): Int {

        return customerList.size
    }
    class  ViewHolder(itemView: View ,clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val tvProfile : TextView =itemView.findViewById(R.id.cuName)

        init {
            itemView.setOnClickListener{

                clickListener.onItemClick(adapterPosition)

            }
        }

    }




}


