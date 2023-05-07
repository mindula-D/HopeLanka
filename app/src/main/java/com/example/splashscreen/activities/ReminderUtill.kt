package com.example.splashscreen.activities

object ReminderUtill {
    fun validateReminderInput(
        remName: String,
        remDate: String,
        remTime: String,
    ): Boolean {
        if (remName.isEmpty() || remDate.isEmpty() || remTime.isEmpty()) {
            return false
        }

        if (remName > 30.toString()){
            return false
        }
        return true
        }
}