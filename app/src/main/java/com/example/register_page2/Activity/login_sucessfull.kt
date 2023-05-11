package com.example.register_page2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.register_page2.R

class login_sucessfull : AppCompatActivity() {

    // login sucessfull page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)

        var  regi_nextBtn =findViewById<Button>(R.id.regi_nextBtn)
        regi_nextBtn.setOnClickListener{

            val intent = Intent (this, Client_Profile ::class.java)
            startActivity(intent)
        }

    }
}