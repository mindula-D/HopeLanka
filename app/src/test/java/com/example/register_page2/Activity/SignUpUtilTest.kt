package com.example.register_page2.Activity

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SignUpUtilTest{
    @Test
    fun `empty email returns false`(){

        val result=SignUpUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
         assertThat(result).isFalse()

    }
    @Test
    fun `valid email and correctly repeated password return true`(){

        val result=SignUpUtil.validateRegistrationInput(
            "senurihimasha68@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isTrue()


    }
    @Test
    fun `username already exists returns false`(){

        val result=SignUpUtil.validateRegistrationInput(
            "himara34@gmail.com",
            "123",
            "123"

        )
        assertThat(result).isFalse()


    }
    @Test
    fun `incorrectly confirmed password returns false`(){

        val result=SignUpUtil.validateRegistrationInput(
            "senurihimasha68@gmail.com",
            "123",
            "555555"

        )
        assertThat(result).isFalse()


    }
    @Test
    fun `empty password returns false`(){

        val result=SignUpUtil.validateRegistrationInput(
            "senurihimasha68@gmail.com",
            "",
            ""

        )
        assertThat(result).isFalse()


    }
    @Test
    fun `email doesnt have @ returns false`(){

        val result=SignUpUtil.validateRegistrationInput(
            "senurihimasha68gmail.com",
            "1236",
            "1236"

        )
        assertThat(result).isFalse()


    }





}