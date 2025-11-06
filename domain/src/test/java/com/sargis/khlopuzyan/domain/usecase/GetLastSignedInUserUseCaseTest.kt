package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetLastSignedInUserUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val getLastSignedInUserUseCase: GetLastSignedInUserUseCase =
        GetLastSignedInUserUseCase(userRepository)

    @Test
    fun `should return correct data`() {
        val testUserName = "SargisKh"
        `when`(userRepository.getLastSignedInUserName()).thenReturn(testUserName)
        val actual: String? = getLastSignedInUserUseCase()

        val expected = "SargisKh"
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `should return incorrect data`() {
        val testUserName = "SargisKhX"
        `when`(userRepository.getLastSignedInUserName()).thenReturn(testUserName)
        val actual: String? = getLastSignedInUserUseCase()

        val expected = "SargisKh"
        Assertions.assertNotEquals(actual, expected)
    }
}