package com.sargis.khlopuzyan.presenter.ui.auth.login

import com.sargis.khlopuzyan.presenter.base.UiEvent

sealed interface LoginUiEvent : UiEvent {
    data class Login(val username: String, val password: String) : LoginUiEvent
    object Register : LoginUiEvent
}