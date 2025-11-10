package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllUsersUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return userRepository.observeAllUser()
    }
}