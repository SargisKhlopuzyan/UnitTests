package com.sargis.khlopuzyan.presenter.ui.main

import com.sargis.khlopuzyan.domain.entity.User

data class MainUiState(
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    var allUsers: List<User> = listOf(),
)