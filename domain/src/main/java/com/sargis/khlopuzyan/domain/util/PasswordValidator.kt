package com.sargis.khlopuzyan.domain.util

import androidx.core.text.isDigitsOnly

object PasswordValidator {
    fun isValidPassword(password: String?): Boolean {
        if (password.isNullOrBlank()) {
            return false
        }

        if (password.length < 4) {
            return false
        }

        if (password.isDigitsOnly()) {
            return false
        }

        if (password.first().isDigit()) {
            return false
        }

        if (password.contains(' ')) {
            return false
        }

        return true
    }
}