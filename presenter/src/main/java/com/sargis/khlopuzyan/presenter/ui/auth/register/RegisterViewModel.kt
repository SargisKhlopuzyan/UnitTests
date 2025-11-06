package com.sargis.khlopuzyan.presenter.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.usecase.GetUserUseCase
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class RegisterViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.onStart {}.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegisterUiState()
    )

    private var _navigationEvent: MutableSharedFlow<RegisterNavigationEvent> = MutableSharedFlow<RegisterNavigationEvent>()
    var navigationEvent: SharedFlow<RegisterNavigationEvent> = _navigationEvent.asSharedFlow()

    fun onEvent(uiEvent: RegisterUiEvent) {
        when (uiEvent) {
            is RegisterUiEvent.Register -> register(uiEvent.firstName, uiEvent.lastName)
        }
    }

    fun register(firstName: String, lastName: String) {
        val param = SaveUserParam(firstName = firstName, lastName = lastName)
        val isSaved = saveUserUseCase(param = param)

    }
}