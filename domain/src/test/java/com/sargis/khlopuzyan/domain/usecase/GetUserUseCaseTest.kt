package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetUserUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val getUserUseCase: GetUserUseCase = GetUserUseCase(userRepository)

    @Test
    fun `should return correct data`() {
        val testUser = User("Sargis", "Khlopuzyan")
        `when`(userRepository.getUser()).thenReturn(testUser)
        val actual: User = getUserUseCase()

        val expected = User("Sargis", "Khlopuzyan")
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `should return incorrect data`() {
        val testUser = User("SargisX", "KhlopuzyanX")
        `when`(userRepository.getUser()).thenReturn(testUser)
        val actual: User = getUserUseCase.invoke()

        val expected = User("Sargis", "Khlopuzyan")
        Assertions.assertNotEquals(actual, expected)
    }
}