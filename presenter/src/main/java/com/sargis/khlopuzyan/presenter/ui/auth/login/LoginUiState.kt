package com.sargis.khlopuzyan.presenter.ui.auth.login

import com.sargis.khlopuzyan.presenter.base.UiState

data class LoginUiState(
    val lastSignedInUsername: String? = null,
    val error: String? = null,
) : UiState