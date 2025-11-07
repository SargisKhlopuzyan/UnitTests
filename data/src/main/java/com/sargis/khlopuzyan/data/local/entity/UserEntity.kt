package com.sargis.khlopuzyan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
)

fun List<UserEntity>.toUserList() = map { noteEntity ->
    noteEntity.toUser()
}

fun UserEntity.toUser() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    username = username,
    password = password
)

fun User.toUserEntity() = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    username = username,
    password = password
)

fun RegisterUserParam.toUserEntity() = UserEntity(
    firstName = firstName,
    lastName = lastName,
    username = username,
    password = password
)