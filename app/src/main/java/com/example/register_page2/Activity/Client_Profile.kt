// for profile form
// importing required libraries and modules
package com.example.register_page2.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.register_page2.R
import com.example.register_page2.Model.CustomerModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Client_Profile : AppCompatActivity() {

    // defining all the required text input fields and buttons
    private lateinit var customerName :TextInputLayout
    private lateinit var email:TextInputLayout
    private lateinit var address:TextInputLayout
    private lateinit var contact :TextInputLayout
    private lateinit var bio :TextInputLayout
    private lateinit var update_pro_btn_id : Button
    private lateinit var dbRef :DatabaseReference

    // function called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {

        // initializing all the input fields and buttons
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_profile)
        customerName = findViewById(R.id.layout_Name)
        email = findViewById(R.id.layout_Email)
        address = findViewById(R.id.layout_Address_pro)
        contact = findViewById(R.id.layout_ContacNum_pro)
        bio= findViewById(R.id.layout_bio_pro)
        update_pro_btn_id = findViewById(R.id.update_pro_btn_id)
        dbRef = FirebaseDatabase.getInstance().getReference( "customer")

        // set an onclick listener on update profile button
        update_pro_btn_id.setOnClickListener {
            saveCustomerData()


        }


        // set an onclick listener on edit image button
        var  EditImg_btn_pro =findViewById<Button>(R.id.EditImg_btn_pro)
        EditImg_btn_pro.setOnClickListener{

            val intent = Intent (this, Image_Edit_profile ::class.java)
            startActivity(intent)
        }

//        var  update_pro_btn =findViewById<Button>(R.id.update_pro_btn_id)
//        update_pro_btn.setOnClickListener{
//
//            val intent = Intent (this, Update_client_profile ::class.java)
//            startActivity(intent)
//        }


    }

    private fun  saveCustomerData(){

        // function to save the customer data
        val input_Name_pro= customerName.editText?.text.toString()
        val input_Email_pro=email.editText?.text.toString()
        val input_Address_pro=address.editText?.text.toString()
        val input_Number=contact.editText?.text.toString()
        val input_bio_pro=bio.editText?.text.toString()


        if (input_Name_pro.isEmpty()||input_Email_pro.isEmpty()||input_Address_pro.isEmpty()||input_Number.isEmpty()||input_bio_pro.isEmpty()){
            Toast.makeText(this, "Please fill in all feields", Toast.LENGTH_SHORT).show()
            return


        }


        val customerId = dbRef.push().key!!
        val customer = CustomerModel(customerId,input_Name_pro,input_Email_pro,input_Address_pro,input_Number,input_bio_pro )

        // save the customer object to the Firebase database
        dbRef.child(customerId).setValue(customer)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show()
                customerName.editText?.text?.clear()
                email.editText?.text?.clear()
                address.editText?.text?.clear()
                contact.editText?.text?.clear()
                bio.editText?.text?.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

        if(!input_Email_pro.contains("@")) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        var  update_pro_btn =findViewById<Button>(R.id.update_pro_btn_id)
        update_pro_btn.setOnClickListener{

            val intent = Intent (this, FetchingActivity ::class.java)
            startActivity(intent)
      }

        // var  update_pro_btn =findViewById<Button>(R.id.update_pro_btn_id)
        // update_pro_btn.setOnClickListener{

//            val intent = Intent (this, FetchingActivity::class.java)
//            startActivity(intent)
        // }


    }



}







