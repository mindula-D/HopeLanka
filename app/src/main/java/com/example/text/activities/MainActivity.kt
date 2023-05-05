package com.example.text.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.text.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create an array of donation types
        val Donation =arrayOf("Clothes","Food","Education","Electronics","Medicine")
        // Find the spinner view in the layout file and set its adapter to use the donation array
        val spinner = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter =ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
            Donation)
        spinner.adapter=arrayAdapter

        // Set an item selected listener for the spinner
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // When an item is selected, display a toast message showing the selected donation type
            Toast.makeText(applicationContext,"Selected donation type is = "+ Donation[position],Toast.LENGTH_SHORT ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}