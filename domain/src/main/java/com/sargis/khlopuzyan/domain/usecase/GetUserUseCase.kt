package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): User? {
        return userRepository.getUser()
    }
}