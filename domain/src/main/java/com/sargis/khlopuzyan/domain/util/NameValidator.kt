package com.sargis.khlopuzyan.domain.util

object NameValidator {

    fun isValidName(name: String?): Boolean {

        if (name.isNullOrBlank()) {
            return false
        }

        if (name.length < 2) {
            return false
        }

        if (name.any { it.isDigit() }) {
            return false
        }

        return true
    }
}