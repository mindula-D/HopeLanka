package com.example.hopelankatest.activities

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AddCardUtillTest{

    @Test
    fun `empty cardNumber returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "",
            month = "1",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty month returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty year returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty cvv returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "23",
            cvv = "",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty cardHolderName returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "23",
            cvv = "123",
            cardHolderName = "",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty bankName returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `filled all feilds returns true`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `cardNumber already exists returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "1234567891234567",
            month = "1",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `month is greater than 12 returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "13",
            year = "23",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `year is greater than 2 degits returns false`(){
        val result = AddCardUtill.validateAddCardInput(
            cardNumber = "123456789012345",
            month = "1",
            year = "233",
            cvv = "123",
            cardHolderName = "Mindula",
            bankName = "Sampath Bank"
        )
        assertThat(result).isFalse()
    }

}