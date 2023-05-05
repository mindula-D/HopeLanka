package com.example.hopelankatest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.hopelankatest.R
import com.example.hopelankatest.models.PaymentModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EnterAmount : AppCompatActivity() {

    // Declare variables for the views and buttons in the layout
    private lateinit var etAmount: TextInputLayout
    private lateinit var btnDonateNow: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_amount)

        // find the enter amount textview
        etAmount = findViewById(R.id.rs)
        // find the donate now button view
        btnDonateNow = findViewById(R.id.btnDonateNow)

        // Get a reference to the "CardDetails" node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("DonationAmount")

        // Set a click listener for the donate now button
        btnDonateNow.setOnClickListener {
            saveDonationAmount()
        }

        // Set a click listener for the back button
        val imageView = findViewById<ImageView>(R.id.back)
        imageView.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    // Function to save the donation amount
    private fun saveDonationAmount() {

        // Get the amount from layout
        val amount = etAmount.editText?.text.toString()

        // Check if field is empty
        if (amount.isEmpty()) {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show()
            return
        }

        //unique ID for the payment
        val paymentId = dbRef.push().key!!

        // Create a new payment object
        val payment = PaymentModel(paymentId, amount)

        // Save the new payment object to the database
        dbRef.child(paymentId).setValue(payment)
            .addOnCompleteListener {
                etAmount.editText?.text?.clear()

                // Launch the PaymentOptions
                val intent = Intent(this, PaymentOptions::class.java).also {
                    //pass the value of amount in to PaymentOptions
                    it.putExtra("amount", amount)
                    startActivity(it)
                }
                startActivity(intent)
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
    }
}

//private fun buttonSendEmail() {
//    val stringSenderEmail = "silversandsfeedback@gmail.com"
//    val stringReceiverEmail = "mindulatharinda99@gmail.com"
//    val stringPasswordSenderEmail = "silversands@3210"
//    val stringHost = "smtp.gmail.com"
//    val properties = Properties()
//    properties["mail.smtp.host"] = stringHost
//    properties["mail.smtp.port"] = "465"
//    properties["mail.smtp.ssl.enable"] = "true"
//    properties["mail.smtp.auth"] = "true"
//    val session: Session = Session.getInstance(properties, object : Authenticator() {
//        override fun getPasswordAuthentication(): PasswordAuthentication {
//            return PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail)
//        }
//    })
//    val mimeMessage = MimeMessage(session)
//    try {
//        mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(stringReceiverEmail))
//        mimeMessage.setSubject("Subject: Android App email")
//        mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email. \n\n Cheers!\nProgrammer World")
//        val thread = Thread {
//            try {
//                Transport.send(mimeMessage)
//            } catch (e: MessagingException) {
//                e.printStackTrace()
//            }
//        }
//        thread.start()
//    } catch (e: MessagingException) {
//        e.printStackTrace()
//    }
//}