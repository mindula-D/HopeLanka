package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hopelankatest.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginSuccess::class.java)
            startActivity(intent)
        }
    }
}