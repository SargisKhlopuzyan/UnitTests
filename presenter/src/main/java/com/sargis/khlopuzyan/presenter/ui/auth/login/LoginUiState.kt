package com.sargis.khlopuzyan.presenter.ui.auth.login

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.presenter.base.UiState

data class LoginUiState(
    val user: User? = null,
    val error: String? = null,
) : UiState