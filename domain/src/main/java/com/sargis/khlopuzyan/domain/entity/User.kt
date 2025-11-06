package com.sargis.khlopuzyan.domain.entity

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)