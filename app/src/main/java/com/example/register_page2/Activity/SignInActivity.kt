package com.example.register_page2.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.example.register_page2.Activity.databinding.ActivitySignInBinding
import com.example.register_page2.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using View Binding
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Authentication instance
        firebaseAuth = FirebaseAuth.getInstance()
        // Set OnClickListener for "Sign Up" TextView
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        // Set OnClickListener for "Sign In" Button
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            // Check if email and password are not empty
            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val firebaseUser = firebaseAuth.currentUser
                        if (firebaseUser != null && firebaseUser.email == email) {
                        val intent = Intent(this, register_form::class.java)
                        startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this,"Invalid email or password!" , Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }

        }



    }

    // direct login to system code
//    override fun onStart() {
//        super.onStart()
//
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this,register_form::class.java)
//            startActivity(intent)
//        }
//    }
}