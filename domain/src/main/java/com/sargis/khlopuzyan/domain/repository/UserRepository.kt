package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.entity.User

interface UserRepository {
    fun getUser(): User?
    fun saveUser(saveParam: SaveUserParam): Boolean
}