package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.repository.UserRepository

class GetLastSignedInUsernameUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): String? {
        return userRepository.getLastSignedInUsername()
    }
}