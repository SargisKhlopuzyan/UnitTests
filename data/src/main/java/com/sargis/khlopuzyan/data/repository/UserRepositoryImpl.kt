package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun getLastSignedInUserName(): String? {
        return "SargisKh"
    }

    override fun getUser(loginUserParam: LoginUserParam): User? {
        return User(
            1,
            userName = "SargisKh",
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            password = "1234"
        )
    }

    override fun registerUser(registerUserParam: RegisterUserParam): User? {
        return if (registerUserParam.userName == "SargisKh") {
            null
        } else {
            User(
                2,
                userName = registerUserParam.userName,
                firstName = registerUserParam.firstName,
                lastName = registerUserParam.lastName,
                password = registerUserParam.password
            )
        }
    }

    override fun isUserExist(userName: String): Boolean {
        return userName.equals("Sargis", true)
    }
}