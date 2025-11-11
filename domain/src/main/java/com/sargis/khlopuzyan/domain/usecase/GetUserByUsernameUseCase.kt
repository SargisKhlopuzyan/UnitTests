package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class GetUserByUsernameUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(username: String): User? {
        return userRepository.getUserByUsername(username)
    }
}