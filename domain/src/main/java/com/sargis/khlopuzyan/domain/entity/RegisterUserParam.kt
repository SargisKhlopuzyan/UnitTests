package com.sargis.khlopuzyan.domain.entity

data class RegisterUserParam(
    val userName: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)