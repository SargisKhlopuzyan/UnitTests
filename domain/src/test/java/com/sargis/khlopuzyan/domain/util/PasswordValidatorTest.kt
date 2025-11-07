package com.sargis.khlopuzyan.domain.util

import org.junit.Test
import org.junit.jupiter.api.Assertions

class PasswordValidatorTest {

    private val passwordValidator = PasswordValidator

    @Test
    fun `validating nullable password should return false`() {
        val testPassword = null
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating blank password should return false`() {
        val testPassword = "            "
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating password with at least 4 letters should return true`() {
        val testPassword = "abcd"
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `validating password with less then 4 letters should return false`() {
        val testPassword = "abc"
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating password with starting with digit should return false`() {
        val testPassword = "1abcde"
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating password with starting with letter and containing digit should return true`() {
        val testPassword = "a1bcde"
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `validating password containing space should return false`() {
        val testPassword = "a1 bcde"
        val actual = passwordValidator.isValidPassword(testPassword)
        Assertions.assertFalse(actual)
    }
}