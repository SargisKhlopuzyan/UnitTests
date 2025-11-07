package com.sargis.khlopuzyan.presenter.ui.auth.register

import com.sargis.khlopuzyan.presenter.base.UiEvent

sealed interface RegisterUiEvent : UiEvent {
    data class Register(
        val firstName: String,
        val lastName: String,
        val username: String,
        val password: String,
    ) : RegisterUiEvent
}