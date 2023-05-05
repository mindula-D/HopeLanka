package com.example.register_page2.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.register_page2.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class Update_client_profile : AppCompatActivity() {

    // Views
    private lateinit var profilePic: ImageView
    private lateinit var chooseButton: Button
    private lateinit var uploadButton: Button

    // Image URI
    private lateinit var imageUri: Uri

    // Firebase storage reference
    private lateinit var storageReference: StorageReference

    // Existing profile picture URL
    private lateinit var existingProfilePicUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_client_profile)

        // Retrieve existing profile picture URL from your database or SharedPreferences
        existingProfilePicUrl = "https://your-existing-profile-pic-url.com"

        // Initialize Views
        profilePic = findViewById(R.id.profilePic)
        chooseButton = findViewById(R.id.chooseButton)
        uploadButton = findViewById(R.id.uploadButton)


        Glide.with(this).load(existingProfilePicUrl).into(profilePic)

        // Initialize Firebase Storage Reference
        storageReference = FirebaseStorage.getInstance().reference

        // Choose Image Button Click Listener
        chooseButton.setOnClickListener {
            choosePicture()
        }

        // Upload Image Button Click Listener
        uploadButton.setOnClickListener {
            uploadPicture()
        }
    }


    // Method to Choose Image from Gallery
    private fun choosePicture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!
            Glide.with(this).load(imageUri).into(profilePic) // Load chosen image into ImageView using Glide library
        }
    }

    // Method to Upload Image to Firebase Storage
    private fun uploadPicture() {
        // Check if Image URI is initialized
        if (!::imageUri.isInitialized) {
            Snackbar.make(findViewById(android.R.id.content), "Please choose an image first.", Snackbar.LENGTH_LONG).show()
            return
        }


        val randomKey = UUID.randomUUID().toString()


        val imageRef = storageReference.child("images/$randomKey")

        // Upload Image
        val uploadTask = imageRef.putFile(imageUri)


        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading Image...")
        progressDialog.show()

        // Handle Upload Success and Failure Listeners
        uploadTask.addOnSuccessListener {
            progressDialog.dismiss()
            Snackbar.make(findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show()
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(applicationContext, "Failed to upload image", Toast.LENGTH_LONG).show()
        }
    }

}