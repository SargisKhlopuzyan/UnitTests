package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User

interface UserRepository {
    fun getLastSignedInUserName(): String?
    fun getUser(loginUserParam: LoginUserParam): User?
    fun registerUser(registerUserParam: RegisterUserParam): User?
    fun isUserExist(userName: String): Boolean
}