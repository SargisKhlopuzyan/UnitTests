package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.UserName
import com.sargis.khlopuzyan.domain.repository.UserRepository

class GetUserNameUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): UserName {
        return userRepository.getName()
    }
}