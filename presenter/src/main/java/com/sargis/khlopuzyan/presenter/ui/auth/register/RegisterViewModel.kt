package com.sargis.khlopuzyan.presenter.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.onStart {}.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegisterUiState()
    )

    private var _navigationEvent: MutableSharedFlow<RegisterNavigationEvent> = MutableSharedFlow()
    var navigationEvent: SharedFlow<RegisterNavigationEvent> = _navigationEvent.asSharedFlow()

    fun onEvent(uiEvent: RegisterUiEvent) {
        when (uiEvent) {
            is RegisterUiEvent.Register -> register(
                uiEvent.userName,
                uiEvent.firstName,
                uiEvent.lastName,
                uiEvent.password
            )
        }
    }

    fun register(userName: String, firstName: String, lastName: String, password: String) {
        val param = RegisterUserParam(
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            password = password
        )
        registerUserUseCase(param)
    }
}