package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.Result
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class RegisterUserUseCaseTest {

    val userRepository: UserRepository = mock()
    val registerUserUseCase = RegisterUserUseCase(userRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should save user if userName wasn't already saved`() {
        val testUserName = "SargisKh_New"
        `when`(userRepository.isUserExist(testUserName)).thenReturn(false)

        val testParam = RegisterUserParam(
            userName = testUserName,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "1234"
        )

        val actual = registerUserUseCase(testParam)

        Assertions.assertInstanceOf(Result.Success::class.java, actual)
    }

    @Test
    fun `should not save user if userName was already saved`() {
        val testUserName = "SargisKh"
        `when`(userRepository.isUserExist(testUserName)).thenReturn(true)

        val testParam = RegisterUserParam(
            userName = testUserName,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "1234"
        )
        val actual = registerUserUseCase(testParam)
        Assertions.assertInstanceOf(Result.Error::class.java, actual)
    }

    @Test
    fun `should call isUserExist function one time`() {
        `when`(userRepository.isUserExist(any())).thenReturn(true)
        val testParam = RegisterUserParam(
            userName = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "1234"
        )
        val actual = registerUserUseCase(testParam)
        Mockito.verify(userRepository, Mockito.times(1)).isUserExist(userName = any())
    }
}