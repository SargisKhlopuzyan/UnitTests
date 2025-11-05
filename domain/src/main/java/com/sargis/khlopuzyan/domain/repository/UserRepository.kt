package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.SaveUserNameParam
import com.sargis.khlopuzyan.domain.entity.UserName

interface UserRepository {
    fun getName(): UserName
    fun saveName(saveParam: SaveUserNameParam): Boolean
}