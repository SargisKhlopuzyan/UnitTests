package com.sargis.khlopuzyan.presenter.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.RegisterUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.onStart {}.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegisterUiState()
    )

    private var _eventFlow: MutableSharedFlow<RegisterNavigationEvent> = MutableSharedFlow()
    var eventFlow: SharedFlow<RegisterNavigationEvent> = _eventFlow.asSharedFlow()

    fun onEvent(uiEvent: RegisterUiEvent) {
        when (uiEvent) {
            is RegisterUiEvent.Register -> register(
                firstName = uiEvent.firstName,
                lastName = uiEvent.lastName,
                username = uiEvent.username,
                password = uiEvent.password
            )
        }
    }

    private fun register(firstName: String, lastName: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val param = RegisterUserParam(
                firstName = firstName,
                lastName = lastName,
                username = username,
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
                        _eventFlow.emit(RegisterNavigationEvent.Registered(userId))
                    } ?: run {
                        _uiState.update {
                            it.copy(error = "Something went wrong")
                        }
                    }
                }
            }
        }
    }
}