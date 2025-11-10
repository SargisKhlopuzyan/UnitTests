package com.sargis.khlopuzyan.presenter.ui.main

import com.sargis.khlopuzyan.domain.entity.User

sealed interface MainUiEvent {
    data class DeleteUser(val user: User) : MainUiEvent
    object SignedOut : MainUiEvent
}