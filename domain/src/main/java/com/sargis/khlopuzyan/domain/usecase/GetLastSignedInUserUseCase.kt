package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.repository.UserRepository

class GetLastSignedInUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): String? {
        return userRepository.getLastSignedInUserName()
    }
}