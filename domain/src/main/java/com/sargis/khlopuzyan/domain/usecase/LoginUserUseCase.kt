package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UsernameValidator

class LoginUserUseCase(
    private val userRepository: UserRepository,
    private val usernameValidator: UsernameValidator,
    private val passwordValidator: PasswordValidator,
) {
    suspend operator fun invoke(param: LoginUserParam): Result<User> {

        if (!usernameValidator.isValidUsername(param.username)) {
            return Result.Error(error = "Incorrect username")
        }

        if (!passwordValidator.isValidPassword(param.password)) {
            return Result.Error(error = "Incorrect password")
        }

        val user = userRepository.getUser(loginUserParam = param)

        if (user == null) {
            return Result.Error(error = "Incorrect username or password")
        }

        return Result.Success(user)
    }
}