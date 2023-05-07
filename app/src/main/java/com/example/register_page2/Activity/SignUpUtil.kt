package com.example.register_page2.Activity

object SignUpUtil {
    private val existingUsers = listOf("senurihimasha@gmail.com","himara34@gmail.com")
    fun validateRegistrationInput(

        email: String,
        pass: String,
        rePass: String

    ): Boolean {
        if (email.isEmpty() || pass.isEmpty()) {
            return false
        }
        if (email in existingUsers) {

            return false
        }
        if (pass != rePass) {
            return false
        }

        if (!email.contains("@")) {
            // Return false if `email` parameter does not contain "@"
            return false
            }
        return true
    }
}