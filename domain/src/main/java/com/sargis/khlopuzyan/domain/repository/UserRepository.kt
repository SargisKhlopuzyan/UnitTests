package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getLastSignedInUsername(): String?
    fun saveLastSignedInUsername(username: String)
    suspend fun observeAllUser(): Flow<List<User>>
    suspend fun getUser(loginUserParam: LoginUserParam): User?
    suspend fun getUserByUsername(username: String): User?
    suspend fun registerUser(registerUserParam: RegisterUserParam): User?
    suspend fun deleteUser(userEntity: User): Int
    suspend fun isUserExist(username: String): Boolean
}