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
    private val nameValidator: NameValidator,
    private val usernameValidator: UsernameValidator,
    private val passwordValidator: PasswordValidator,
) {
    suspend operator fun invoke(param: RegisterUserParam): Result<User> {

        val isUserExist = userRepository.isUserExist(param.username)

        if (isUserExist) {
            return Result.Error(error = "Username already exist")
        }

        if (!nameValidator.isValidName(param.firstName)) {
            return Result.Error(error = "Invalid first name")
        }

        if (!nameValidator.isValidName(param.lastName)) {
            return Result.Error(error = "Invalid last name")
        }

        if (!usernameValidator.isValidUsername(param.username)) {
            return Result.Error(error = "Invalid username")
        }

        if (!passwordValidator.isValidPassword(param.password)) {
            return Result.Error(error = "Invalid password")
        }

        val registeredUser = userRepository.registerUser(registerUserParam = param)

        if (registeredUser == null) {
            return Result.Error(error = "Registration failed")
        }

        return Result.Success(registeredUser)
    }
}