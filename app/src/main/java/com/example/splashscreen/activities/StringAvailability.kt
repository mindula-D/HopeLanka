package com.example.splashscreen.activities

import android.content.Context

class StringAvailability {

    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
        }
}