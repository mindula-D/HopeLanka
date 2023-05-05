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
        val Donation =arrayOf("Clothes","Food","Education","Electronics","Medicine")
        val spinner = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter =ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
            Donation)
        spinner.adapter=arrayAdapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Toast.makeText(applicationContext,"Selected donation type is = "+ Donation[position],Toast.LENGTH_SHORT ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}