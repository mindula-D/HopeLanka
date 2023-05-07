package com.example.text.activities
import android.content.Context

class PackageCheck {

    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }
}