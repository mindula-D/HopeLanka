package com.example.text.activities

import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat


class ItemUtillTest{
    @Test
    fun `empty name returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="",
            number = "0718015967",
            type = "clothes",
            email ="Nilmini@gmail.com",
            description = "Donating eldery clothes"
            )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty number returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "",
            type = "clothes",
            email ="Nilmini@gmail.com",
            description = "Donating eldery clothes"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty type returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "0718015967",
            type = "",
            email ="Nilmini@gmail.com",
            description = "Donating eldery clothes"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `empty email returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "0718015967s",
            type = "clothes",
            email ="",
            description = "Donating eldery clothes"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty description returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "0718015967",
            type = "clothes",
            email ="Nilmini@gmail.com",
            description = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `number field has letters returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "0718015967s",
            type = "clothes",
            email ="Nilmini@gmail.com",
            description = "Donating eldery clothes"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `email doesnt have @ returns false`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "07180159671",
            type = "clothes",
            email ="Nilminigmail.com",
            description = "Donating eldery clothes"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `all fields are filled returns true`(){
        val result = ItemUtill.validateAddItemInput(
            name ="Nilmini Kumarasinghe",
            number = "07180159671",
            type = "clothes",
            email ="Nilmini@gmail.com",
            description = "Donating eldery clothes"
        )
        assertThat(result).isTrue()
    }



}


