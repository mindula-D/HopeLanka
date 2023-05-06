package com.example.register_page2.Activity

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.example.register_page2.R
import org.junit.After

class StringCompareTest {
    private lateinit var stringCompare: StringCompare

    @Before
    fun setup() {
        stringCompare = StringCompare()
    }

    @Test
    fun stringNameSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = stringCompare.isEqual(context, R.string.app_name, "Register_page2")
        assertThat(result).isFalse()
    }

    @Test
    fun stringNameDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = stringCompare.isEqual(context, R.string.app_name, "Hello")
        assertThat(result).isFalse()
    }

    @After
    fun tearDown() {

    }

}