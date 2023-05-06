package com.example.text.activities
object ItemUtill {
    fun validateAddItemInput(
        name: String,
        number: String,
        type: String,
        email: String,
        description: String,
        ): Boolean {
        if (name.isEmpty() || number.isEmpty() || type.isEmpty() || email.isEmpty() || description.isEmpty() ){
            return false }
        if(number > 10.toString()){
            return false }
        if (!number.matches(Regex("\\d+"))) {
            // Return false if `number` parameter contains any non-digit characters
            return false }
        if (!email.contains("@")) {
            // Return false if `email` parameter does not contain "@"
            return false }
        return true
    }

}
