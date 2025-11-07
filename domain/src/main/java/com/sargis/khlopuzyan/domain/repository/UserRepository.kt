package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User

interface UserRepository {
    fun getLastSignedInUsername(): String?
    fun saveLastSignedInUsername(username: String)
    suspend fun getUser(loginUserParam: LoginUserParam): User?
    suspend fun registerUser(registerUserParam: RegisterUserParam): User?
    suspend fun isUserExist(username: String): Boolean
}