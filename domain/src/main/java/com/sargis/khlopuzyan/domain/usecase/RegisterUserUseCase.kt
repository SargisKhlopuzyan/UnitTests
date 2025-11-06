package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import com.sargis.khlopuzyan.domain.util.NameValidator
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UsernameValidator

class RegisterUserUseCase(
    private val userRepository: UserRepository,
    private val usernameValidator: UsernameValidator,
    private val nameValidator: NameValidator,
    private val passwordValidator: PasswordValidator,
) {
    operator fun invoke(param: RegisterUserParam): Result<User> {
        val isUserExist = userRepository.isUserExist(param.username)

        if (isUserExist) {
            return Result.Error(error = "User already exist", data = null)
        }

        if (!usernameValidator.isValidUsername(param.username)) {
            return Result.Error(error = "Invalid username", data = null)
        }

        if (!nameValidator.isValidName(param.firstName)) {
            return Result.Error(error = "Invalid first name", data = null)
        }

        if (!nameValidator.isValidName(param.lastName)) {
            return Result.Error(error = "Invalid last name", data = null)
        }

        if (!passwordValidator.isValidPassword(param.password)) {
            return Result.Error(error = "Invalid password", data = null)
        }

        val registeredUser = userRepository.registerUser(registerUserParam = param)
        return Result.Success(registeredUser)
    }
}