package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
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
    fun `should not save data if userName was already saved`() {
        val testUser = User(1, "SargisKh", "Sargis", "Khlopuzyan", "1234")
        `when`(userRepository.isUserExist(testUser.userName)).thenReturn(true)

        val testParam = RegisterUserParam(
            userName = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "1234"
        )
        val actual = registerUserUseCase(testParam)
        val expected = Result.Error<String>("")
        Assertions.assertInstanceOf(Result.Error::class.java, actual)

//        Mockito.verify(userRepository, Mockito.never()).saveName(saveParam = any())
    }

//    @Test
//    fun `should not call saveName function if name was already saved`() {
//        val testUser = User(1, "SargisKh", "Sargis", "Khlopuzyan", "1234")
//        `when`(userRepository.getUser()).thenReturn(testUser)
//        RegisterUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
//        Mockito.verify(userRepository, Mockito.never()).saveUser(saveParam = any())
//    }
//
//    @Test
//    fun `should return true if save was successful`() {
//        val testUser = User(1, "SargisKh", "Sargis", "Khlopuzyan", "1234")
//        `when`(userRepository.getUser()).thenReturn(testUser)
//
//        val testParam = RegisterUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
//        `when`(userRepository.saveUser(saveParam = testParam)).thenReturn(true)
//
//        val actual = registerUserUseCase(testParam)
//
//        val expected = true
//        Assertions.assertEquals(expected, actual)
//
////        Mockito.verify(userRepository, Mockito.atMostOnce()).saveName(saveParam = any())
//    }
//
//    @Test
//    fun `should call saveName if save was successful`() {
//        val testUser = User(1, "SargisKh", "Sargis", "Khlopuzyan", "1234")
//        `when`(userRepository.getUser()).thenReturn(testUser)
//
//        val testParam = RegisterUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
//        `when`(userRepository.saveUser(saveParam = testParam)).thenReturn(true)
//
//        registerUserUseCase(testParam)
//        Mockito.verify(userRepository, Mockito.times(1)).saveUser(saveParam = any())
////        Mockito.verify(userRepository, Mockito.atMostOnce()).saveName(saveParam = any())
//    }
}