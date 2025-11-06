package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.NameValidator
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UsernameValidator
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
        usernameValidator,
        nameValidator,
        passwordValidator
    )

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should save user if userName wasn't already saved`() {
        val testUsername = "SargisKh_New"
        `when`(userRepository.isUserExist(testUsername)).thenReturn(false)

        val testParam = RegisterUserParam(
            username = testUsername,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "a1234"
        )

        val actual = registerUserUseCase(testParam)

        Assertions.assertInstanceOf(Result.Success::class.java, actual)
    }

    @Test
    fun `should not save user if userName was already saved`() {
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
    fun `should call isUserExist function one time`() {
        `when`(userRepository.isUserExist(any())).thenReturn(true)
        val testParam = RegisterUserParam(
            username = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "a1234"
        )
        val actual = registerUserUseCase(testParam)
        Mockito.verify(userRepository, Mockito.times(1)).isUserExist(userName = any())
    }
}