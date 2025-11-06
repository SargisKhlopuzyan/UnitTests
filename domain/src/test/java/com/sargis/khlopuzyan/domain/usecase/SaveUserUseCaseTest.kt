package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveUserUseCaseTest {

    val userRepository: UserRepository = mock()
    val saveUserUseCase = SaveUserUseCase(userRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should not save data if name was already saved`() {
        val testUser = User(firstName = "Sargis", lastName = "Khlopuzyan")
        `when`(userRepository.getUser()).thenReturn(testUser)

        val testParam = SaveUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
        val actual = saveUserUseCase(testParam)
        val expected = true
        Assertions.assertEquals(expected, actual)

//        Mockito.verify(userRepository, Mockito.never()).saveName(saveParam = any())
    }

    @Test
    fun `should not call saveName function if name was already saved`() {
        val testUser = User(firstName = "Sargis", lastName = "Khlopuzyan")
        `when`(userRepository.getUser()).thenReturn(testUser)
        SaveUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
        Mockito.verify(userRepository, Mockito.never()).saveUser(saveParam = any())
    }

    @Test
    fun `should return true if save was successful`() {
        val testUser = User(firstName = "SargisX", lastName = "Khlopuzyan")
        `when`(userRepository.getUser()).thenReturn(testUser)

        val testParam = SaveUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
        `when`(userRepository.saveUser(saveParam = testParam)).thenReturn(true)

        val actual = saveUserUseCase(testParam)

        val expected = true
        Assertions.assertEquals(expected, actual)

//        Mockito.verify(userRepository, Mockito.atMostOnce()).saveName(saveParam = any())
    }

    @Test
    fun `should call saveName if save was successful`() {
        val testUser = User(firstName = "SargisX", lastName = "Khlopuzyan")
        `when`(userRepository.getUser()).thenReturn(testUser)

        val testParam = SaveUserParam(firstName = "Sargis", lastName = "Khlopuzyan")
        `when`(userRepository.saveUser(saveParam = testParam)).thenReturn(true)

        saveUserUseCase(testParam)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(saveParam = any())
//        Mockito.verify(userRepository, Mockito.atMostOnce()).saveName(saveParam = any())
    }
}