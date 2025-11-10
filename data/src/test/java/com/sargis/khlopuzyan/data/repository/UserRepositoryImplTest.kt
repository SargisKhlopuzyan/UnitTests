package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.local.entity.UserEntity
import com.sargis.khlopuzyan.data.local.source.UserDataSource
import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset

class UserRepositoryImplTest {

    private val userDataSource: UserDataSource = mock()
    private val userRepository: UserRepositoryImpl = UserRepositoryImpl(userDataSource)

    @AfterEach
    fun tearDown() {
        reset(userDataSource)
    }

    @Test
    fun `calling_isUserExist_returnsTrueIfUserAlreadySaved`() = runTest {
        val testUsername = "SargisKh1"
        val testUserEntity = UserEntity(
            id = 1,
            firstName = "Sargis",
            lastName = "Sargis",
            username = testUsername,
            password = "a1234"
        )
        `when`(userDataSource.getUserByUsername(testUsername)).thenReturn(testUserEntity)

        val actual = userRepository.isUserExist(testUsername)
        Assertions.assertTrue(actual)
    }

    @Test
    fun `calling_getUser_returnsUserIfUserAlreadySaved`() = runTest {
        val testUsername = "SargisKh2"
        val testPassword = "a1234"

        val testUserEntity = UserEntity(
            id = 1,
            firstName = "Sargis",
            lastName = "Sargis",
            username = testUsername,
            password = testPassword
        )
        `when`(userDataSource.getUserByUsernameAndPassword(testUsername, testPassword))
            .thenReturn(testUserEntity)

        val testLoginUserParam = LoginUserParam(testUsername, testPassword)
        val actual = userRepository.getUser(testLoginUserParam)
        Assertions.assertNotNull(actual)
    }

    @Test
    fun `calling_registerUser_returnsUserIfUserAlreadyNotSaved`() = runTest {
        val testUsername = "SargisKh3"
        val testPassword = "a1234"

        `when`(userRepository.isUserExist(testUsername))
            .thenReturn(false)

        val testRegisterUserParam = RegisterUserParam(
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = testUsername,
            password = testPassword,
        )

        val testUserEntity = UserEntity(
//            id = 1,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = testUsername,
            password = testPassword,
        )

        `when`(userDataSource.insertUser(testUserEntity)).thenReturn(1)

        val actual = userRepository.registerUser(testRegisterUserParam)

        Assertions.assertNotNull(actual)
        Assertions.assertEquals(actual?.id, 1)
    }
}