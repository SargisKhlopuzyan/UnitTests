package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: User): Int {
        return userRepository.deleteUser(user)
    }
}