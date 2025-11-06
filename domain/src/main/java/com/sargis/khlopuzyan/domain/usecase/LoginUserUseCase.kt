package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.Result

class LoginUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(param: LoginUserParam): Result<User> {

        if (param.userName.isBlank()) {
            return Result.Error(error = "Username can't be empty", data = null)
        }

        if (param.password.isBlank()) {
            return Result.Error(error = "password can't be empty", data = null)
        }

        val user = userRepository.getUser(loginUserParam = param)

        if (user != null) {
            return Result.Error(error = "Incorrect username or password", data = null)
        }

        return Result.Success(user)
    }
}