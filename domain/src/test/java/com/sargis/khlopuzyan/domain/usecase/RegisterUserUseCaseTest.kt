package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.NameValidator
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UsernameValidator
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class RegisterUserUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val usernameValidator: UsernameValidator = mock()
    private val nameValidator: NameValidator = mock()
    private val passwordValidator: PasswordValidator = mock()

    val registerUserUseCase = RegisterUserUseCase(
        userRepository,
        nameValidator,
        usernameValidator,
        passwordValidator
    )

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should save user if username wasn't already saved`() = runTest {
        val testParam = RegisterUserParam(
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh",
            password = "a1234"
        )

        `when`(userRepository.isUserExist(testParam.username)).thenReturn(false)
        `when`(nameValidator.isValidName(testParam.firstName)).thenReturn(true)
        `when`(nameValidator.isValidName(testParam.lastName)).thenReturn(true)
        `when`(usernameValidator.isValidUsername(testParam.username)).thenReturn(true)
        `when`(passwordValidator.isValidPassword(testParam.password)).thenReturn(true)

        val testUser = User(
            id = 1,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh",
            password = "a1234"
        )

        `when`(userRepository.registerUser(testParam)).thenReturn(testUser)

        val actual = registerUserUseCase(testParam)

        Assertions.assertInstanceOf(Result.Success::class.java, actual)
    }

    @Test
    fun `should not save user if username was already saved`() = runTest {
        val testUsername = "SargisKh"
        `when`(userRepository.isUserExist(testUsername)).thenReturn(true)

        val testParam = RegisterUserParam(
            username = testUsername,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "a1234"
        )
        val actual = registerUserUseCase(testParam)
        Assertions.assertInstanceOf(Result.Error::class.java, actual)
    }

    @Test
    fun `should call isUserExist function one time`() = runTest {
        `when`(userRepository.isUserExist(any())).thenReturn(true)
        val testParam = RegisterUserParam(
            username = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "a1234"
        )
        val actual = registerUserUseCase(testParam)
        Mockito.verify(userRepository, Mockito.times(1)).isUserExist(username = any())
    }
}