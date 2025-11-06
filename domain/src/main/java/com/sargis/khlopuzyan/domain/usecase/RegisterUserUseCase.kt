package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.Result

class RegisterUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(param: RegisterUserParam): Result<User> {
        val isUserExist = userRepository.isUserExist(param.userName)

        if (isUserExist) {
            return Result.Error(error = "User already exist", data = null)
        }

        val registeredUser = userRepository.registerUser(registerUserParam = param)
        return Result.Success(registeredUser)
    }
}