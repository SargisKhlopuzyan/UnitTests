package com.sargis.khlopuzyan.data.local.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserSharedPref(val context: Context) {

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    }

    fun saveLastSignedInUsername(username: String) {
        sharedPref.edit {
            putString("username", username)
        }
    }

    fun getLastSignedInUsername(): String? {
        return sharedPref.getString("username", null)
    }
}