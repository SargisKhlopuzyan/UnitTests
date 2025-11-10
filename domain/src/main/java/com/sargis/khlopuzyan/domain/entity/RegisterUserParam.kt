package com.sargis.khlopuzyan.domain.entity

data class RegisterUserParam(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
)