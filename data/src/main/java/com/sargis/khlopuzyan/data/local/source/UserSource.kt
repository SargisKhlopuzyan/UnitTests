package com.sargis.khlopuzyan.data.local.source

import com.sargis.khlopuzyan.data.local.dao.UserDao
import com.sargis.khlopuzyan.data.local.entity.UserEntity
import com.sargis.khlopuzyan.data.local.sharedPreferences.UserSharedPref
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getLastSignedInUsername(): String?
    fun saveLastSignedInUsername(username: String)
    fun getUsers(): Flow<List<UserEntity>>
    suspend fun getUserById(id: Int): UserEntity?
    suspend fun getUserByUsername(username: String): UserEntity?
    suspend fun getUserByUsernameAndPassword(username: String, password: String): UserEntity?
    suspend fun insertUser(node: UserEntity): Int
    suspend fun deleteUser(node: UserEntity)
}

class UserDataSourceImpl(
    val userSharedPref: UserSharedPref,
    val dao: UserDao,
) : UserDataSource {
    override fun getLastSignedInUsername(): String? {
        return userSharedPref.getLastSignedInUsername()
    }

    override fun saveLastSignedInUsername(username: String) {
        userSharedPref.saveLastSignedInUsername(username)
    }

    override fun getUsers(): Flow<List<UserEntity>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity? {
        return dao.getUserById(id)
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return dao.getUserByUsername(username)
    }

    override suspend fun getUserByUsernameAndPassword(
        username: String,
        password: String,
    ): UserEntity? {
        return dao.getUserByUsernameAndPassword(username, password)
    }

    override suspend fun insertUser(node: UserEntity): Int {
        return dao.insertUser(node).toInt()
    }

    override suspend fun deleteUser(node: UserEntity) {
        dao.deleteUser(node)
    }
}