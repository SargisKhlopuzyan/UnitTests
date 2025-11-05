package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.domain.entity.SaveUserNameParam
import com.sargis.khlopuzyan.domain.entity.UserName
import com.sargis.khlopuzyan.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getName(): UserName {
        return UserName(firstName = "Sargis", name = "Saqo")
    }

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        return true
    }
}