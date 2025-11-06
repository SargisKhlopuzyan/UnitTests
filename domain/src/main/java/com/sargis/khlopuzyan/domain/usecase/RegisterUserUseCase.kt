package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.Result

class RegisterUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(param: SaveUserParam): Result<Boolean> {
        val existingUser = userRepository.getUser()

        if (existingUser != null) {
            return Result.Error(error = "User already exist", data = null)
        }

        val result = userRepository.saveUser(saveParam = param)
        return Result.Success(result)
    }
}