package com.example.register_page2.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.register_page2.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class SignUp: AppCompatActivity()  {

    private lateinit var  binding:ActivitySignupBinding
    private lateinit var firebaseAuth:FirebaseAuth
    // Define onCreate() method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get an instance of FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity ::class.java)
            startActivity(intent)
        }



        binding.button.setOnClickListener{
            // Get email, password, and confirmed password from the EditText fields
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val rePass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty()&& pass.isNotEmpty()&& rePass.isNotEmpty()){
                if (pass == rePass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{

                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()

            }
        }

    }




    }
