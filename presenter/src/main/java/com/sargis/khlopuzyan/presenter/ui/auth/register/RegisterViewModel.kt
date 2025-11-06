package com.sargis.khlopuzyan.presenter.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.util.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

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
            username = userName,
            firstName = firstName,
            lastName = lastName,
            password = password
        )
        val result = registerUserUseCase(param)

        when (result) {
            is Result.Error<*> -> _uiState.update {
                it.copy(
                    error = result.error
                )
            }

            is Result.Success<User> -> {
                result.data?.id?.let { userId ->
                    _navigationEvent.tryEmit(RegisterNavigationEvent.Registered(userId))
                } ?: run {
                    _uiState.update {
                        it.copy(error = "Something went wrong")
                    }
                }
            }
        }
    }
}