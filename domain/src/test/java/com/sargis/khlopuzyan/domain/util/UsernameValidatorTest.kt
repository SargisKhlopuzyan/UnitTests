package com.sargis.khlopuzyan.domain.util

import org.junit.Test
import org.junit.jupiter.api.Assertions

class UsernameValidatorTest {

    private val usernameValidator = UsernameValidator

    @Test
    fun `validating nullable username should return false`() {
        val testUsername = null
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating blank username should return false`() {
        val testUsername = "            "
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating username with more at least 4 letters should return true`() {
        val testUsername = "abcd"
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `validating username with less then 4 letters should return false`() {
        val testUsername = "abc"
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating username with starting with digit should return false`() {
        val testUsername = "1abcde"
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating username with starting with letter and containing digit should return true`() {
        val testUsername = "a1bcde"
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `validating username containing space should return false`() {
        val testUsername = "a1 bcde"
        val actual = usernameValidator.isValidUsername(testUsername)
        Assertions.assertFalse(actual)
    }
}