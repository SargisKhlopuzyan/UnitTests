package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUser(): User {
        return User(1, firstName = "Sargis", lastName = "Khlopuzyan")
    }

    override fun saveUser(saveParam: SaveUserParam): Boolean {
        return true
    }
}