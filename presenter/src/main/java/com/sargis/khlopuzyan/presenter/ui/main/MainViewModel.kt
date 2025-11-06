package com.sargis.khlopuzyan.presenter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<MainUiState>(MainUiState())

    val uiState: StateFlow<MainUiState> = _uiState.onStart {
//        fetchUser()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MainUiState()
    )

    fun onEvent(uiEvent: MainUiEvent) {
        when (uiEvent) {
            is MainUiEvent.SignedOut -> {}
        }
    }
}