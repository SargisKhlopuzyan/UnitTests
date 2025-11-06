package com.sargis.khlopuzyan.presenter.ui.auth.register

sealed interface RegisterNavigationEvent {
    object NavigateUp : RegisterNavigationEvent
    data class Registered(val userId: Int) : RegisterNavigationEvent
}