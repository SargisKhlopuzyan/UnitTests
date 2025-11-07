package com.sargis.khlopuzyan.domain.util

import org.junit.Test
import org.junit.jupiter.api.Assertions

class NameValidatorTest {

    private val nameValidator = NameValidator

    @Test
    fun `validating nullable name should return false`() {
        val testName = null
        val actual = nameValidator.isValidName(testName)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating blank name should return false`() {
        val testName = "            "
        val actual = nameValidator.isValidName(testName)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating name with at least 2 letters should return true`() {
        val testName = "ab"
        val actual = nameValidator.isValidName(testName)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `validating name with less then 2 letters should return false`() {
        val testName = "a"
        val actual = nameValidator.isValidName(testName)
        Assertions.assertFalse(actual)
    }

    @Test
    fun `validating name with digit should return false`() {
        val testName = "ab1"
        val actual = nameValidator.isValidName(testName)
        Assertions.assertFalse(actual)
    }
}