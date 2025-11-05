package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.UserName
import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetUserNameUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val getUserNameUseCase: GetUserNameUseCase = GetUserNameUseCase(userRepository)

    @Test
    fun `should return correct data`() {
        val testUserName = UserName("Sargis", "Khlopuzyan")
        `when`(userRepository.getName()).thenReturn(testUserName)
        val actual: UserName = getUserNameUseCase()

        val expected = UserName("Sargis", "Khlopuzyan")
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `should return incorrect data`() {
        val testUserName = UserName("SargisX", "KhlopuzyanX")
        `when`(userRepository.getName()).thenReturn(testUserName)
        val actual: UserName = getUserNameUseCase.invoke()

        val expected = UserName("Sargis", "Khlopuzyan")
        Assertions.assertNotEquals(actual, expected)
    }
}