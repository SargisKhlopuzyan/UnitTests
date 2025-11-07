package com.sargis.khlopuzyan.domain.util

import androidx.core.text.isDigitsOnly

object UsernameValidator {

    fun isValidUsername(username: String?): Boolean {

        if (username.isNullOrBlank()) {
            return false
        }

        if (username.length < 4) {
            return false
        }

        if (username.first().isDigit()) {
            return false
        }

        if (username.contains(' ')) {
            return false
        }

        return true
    }
}