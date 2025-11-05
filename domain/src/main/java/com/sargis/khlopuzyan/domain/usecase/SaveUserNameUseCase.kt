package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.SaveUserNameParam
import com.sargis.khlopuzyan.domain.entity.UserName
import com.sargis.khlopuzyan.domain.repository.UserRepository

class SaveUserNameUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(param: SaveUserNameParam): Boolean {
        val oldUserName = userRepository.getName()

        if (oldUserName.firstName == param.name) {
            return true
        }

        val result = userRepository.saveName(saveParam = param)
        return result
    }
}