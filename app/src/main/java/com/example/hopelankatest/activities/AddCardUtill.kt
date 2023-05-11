package com.example.hopelankatest.activities

object AddCardUtill {

    private val existingCards = listOf("1234567891234567")

    fun validateAddCardInput(
        cardNumber: String,
        month: String,
        year: String,
        cvv: String,
        cardHolderName: String,
        bankName: String
    ): Boolean {
        if (cardNumber.isEmpty() || month.isEmpty() || year.isEmpty() || cvv.isEmpty() || cardHolderName.isEmpty() || bankName.isEmpty()) {
            return false
        }
        if (cardNumber in existingCards){
            return false
        }
        if(month > 12.toString()){
            return false
        }
        if (year.count{it.isDigit()} > 2){
            return false
        }
        return true
    }
}

