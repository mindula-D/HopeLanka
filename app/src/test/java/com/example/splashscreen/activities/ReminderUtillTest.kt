package com.example.splashscreen.activities

import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ReminderUtillTest {
    @Test
    fun `empty remName returns false`() {
        val result = ReminderUtill.validateReminderInput(
            remName = "",
            remDate = "03.12",
            remTime = "02",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty remDate returns false`() {
        val result = ReminderUtill.validateReminderInput(
            remName = "Check emails",
            remDate = "",
            remTime = "02",
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `empty remTime returns false`() {
        val result = ReminderUtill.validateReminderInput(
            remName = "Check emails",
            remDate = "03.12",
            remTime = "",
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `remName is greater than thirty returns false`() {
        val result = ReminderUtill.validateReminderInput(
            remName = "Check emailsssssssssssssssssssssssssssssssssssssssssssssss",
            remDate = "03.12",
            remTime = "02",
        )
        assertThat(result).isFalse()
    }
}
