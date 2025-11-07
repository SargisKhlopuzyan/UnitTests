package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetLastSignedInUserUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val getLastSignedInUserUseCase = GetLastSignedInUserUseCase(userRepository)

    @Test
    fun `should return correct data`() {
        val testUsername = "SargisKh"
        `when`(userRepository.getLastSignedInUsername()).thenReturn(testUsername)
        val actual: String? = getLastSignedInUserUseCase()

        val expected = "SargisKh"
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `should return incorrect data`() {
        val testUsername = "SargisKhX"
        `when`(userRepository.getLastSignedInUsername()).thenReturn(testUsername)
        val actual: String? = getLastSignedInUserUseCase()

        val expected = "SargisKh"
        Assertions.assertNotEquals(actual, expected)
    }

    @Test
    fun `should call getLastSignedInUsername function one time`() {
        val testUsername = "SargisKh"
        `when`(userRepository.getLastSignedInUsername()).thenReturn(testUsername)
        val actual: String? = getLastSignedInUserUseCase()
        Mockito.verify(userRepository, Mockito.times(1)).getLastSignedInUsername()
    }
}