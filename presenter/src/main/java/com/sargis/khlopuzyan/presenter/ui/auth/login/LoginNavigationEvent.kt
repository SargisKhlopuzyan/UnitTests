package com.sargis.khlopuzyan.presenter.ui.auth.login

sealed interface LoginNavigationEvent {
    object NavigateUp : LoginNavigationEvent
    data class AuthSuccess(val userId: Int) : LoginNavigationEvent
}