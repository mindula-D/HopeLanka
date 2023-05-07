package com.example.register_page2.Activity

import android.content.Context



    class StringCompare {

        fun isEqual(context: Context, resId: Int, string: String): Boolean {
            return context.getString(resId) == string
        }
}