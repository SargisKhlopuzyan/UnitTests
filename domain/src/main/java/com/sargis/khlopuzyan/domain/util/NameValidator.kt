package com.sargis.khlopuzyan.domain.util

object NameValidator {
    fun isValidName(username: String?): Boolean {
        if (username.isNullOrBlank()) {
            return false
        }

        if (username.length < 2) {
            return false
        }

        if (username.any { it.isDigit() }) {
            return false
        }

        return true
    }
}