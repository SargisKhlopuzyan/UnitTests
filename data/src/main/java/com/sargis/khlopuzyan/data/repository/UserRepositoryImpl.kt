package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.local.entity.toUser
import com.sargis.khlopuzyan.data.local.entity.toUserEntity
import com.sargis.khlopuzyan.data.local.source.UserDataSource
import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override fun getLastSignedInUsername(): String? {
        return userDataSource.getLastSignedInUsername()
    }

    override fun saveLastSignedInUsername(username: String) {
        userDataSource.saveLastSignedInUsername(username)
    }

    override suspend fun observeAllUser(): Flow<List<User>> {
        return userDataSource.observeAllUsers().map {
            it.map {
                it.toUser()
            }
        }
    }

    override suspend fun getUser(loginUserParam: LoginUserParam): User? {
        return userDataSource.getUserByUsernameAndPassword(
            loginUserParam.username,
            loginUserParam.password
        )?.toUser()
    }

    override suspend fun registerUser(registerUserParam: RegisterUserParam): User? {
        if (isUserExist(registerUserParam.username)) {
            return null
        }

        val userEntity = registerUserParam.toUserEntity()
        val id = userDataSource.insertUser(userEntity)

        return userEntity.toUser().copy(id = id)
    }

    override suspend fun deleteUser(userEntity: User): Int {
        return userDataSource.deleteUser(userEntity.toUserEntity())
    }

    override suspend fun isUserExist(username: String): Boolean {
        return userDataSource.getUserByUsername(username) != null
    }
}