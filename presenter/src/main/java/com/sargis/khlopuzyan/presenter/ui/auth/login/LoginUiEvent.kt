package com.sargis.khlopuzyan.presenter.ui.auth.login

import com.sargis.khlopuzyan.presenter.base.UiEvent

sealed interface LoginUiEvent : UiEvent {
    data class Save(val firstName: String, val lastName: String) : LoginUiEvent
    object Register : LoginUiEvent
}