package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UsernameValidator
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class LoginUserUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val usernameValidator: UsernameValidator = mock()
    private val passwordValidator: PasswordValidator = mock()

    private val loginUserUseCase = LoginUserUseCase(
        userRepository,
        usernameValidator,
        passwordValidator
    )

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should return success if username and password are valid`() = runTest {
        val loginParam = LoginUserParam(username = "SargisKh", password = "a1234")

        `when`(usernameValidator.isValidUsername(loginParam.username)).thenReturn(true)
        `when`(passwordValidator.isValidPassword(loginParam.password)).thenReturn(true)

        val testUser = User(
            id = 1,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = loginParam.username,
            password = loginParam.password,
        )

        `when`(userRepository.getUser(loginParam)).thenReturn(testUser)

        val actual = loginUserUseCase(loginParam)

        Assertions.assertInstanceOf(Result.Success::class.java, actual)
    }

}