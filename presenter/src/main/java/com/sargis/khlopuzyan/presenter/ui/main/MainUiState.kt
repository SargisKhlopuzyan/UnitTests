package com.sargis.khlopuzyan.presenter.ui.main

import com.sargis.khlopuzyan.domain.entity.User

data class MainUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val username: String? = null,
    var allUsers: List<User> = listOf(),
)