package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun getLastSignedInUsername(): String? {
        return "SargisKh"
    }

    override fun getUser(loginUserParam: LoginUserParam): User? {
        return User(
            1,
            username = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "a1234"
        )
    }

    override fun registerUser(registerUserParam: RegisterUserParam): User? {
        if (isUserExist(registerUserParam.username)) {
            return null
        }

        return User(
            2,
            username = registerUserParam.username,
            firstName = registerUserParam.firstName,
            lastName = registerUserParam.lastName,
            password = registerUserParam.password
        )
    }

    override fun isUserExist(userName: String): Boolean {
        return userName.equals("Sargis", true)
    }
}