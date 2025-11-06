package com.sargis.khlopuzyan.presenter.ui.main

sealed interface MainUiEvent {
    object SignedOut : MainUiEvent
}