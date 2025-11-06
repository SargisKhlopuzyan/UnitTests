package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.repository.UserRepository

class SaveUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(param: SaveUserParam): Boolean {
        val oldUser = userRepository.getUser()

        if (oldUser?.firstName == param.firstName) {
            return true
        }

        val result = userRepository.saveUser(saveParam = param)
        return result
    }
}