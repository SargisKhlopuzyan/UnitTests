package com.sargis.khlopuzyan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sargis.khlopuzyan.domain.entity.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)

fun List<UserEntity>.toUserList() = map { noteEntity ->
    noteEntity.toUser()
}

fun UserEntity.toUser() = User(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
    password = password
)

fun User.toUserEntity() = UserEntity(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
    password = password
)