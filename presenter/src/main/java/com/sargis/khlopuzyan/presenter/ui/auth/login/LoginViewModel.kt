package com.sargis.khlopuzyan.presenter.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.GetLastSignedInUserUseCase
import com.sargis.khlopuzyan.domain.usecase.LoginUserUseCase
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

class LoginViewModel(
    private val getLastSignedInUserUseCase: GetLastSignedInUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<LoginUiState>(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState.onStart {
        fetchLastSignedInUser()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginUiState()
    )

    private var _eventFlow: MutableSharedFlow<LoginNavigationEvent> = MutableSharedFlow()
    val eventFlow: SharedFlow<LoginNavigationEvent> = _eventFlow.asSharedFlow()

    fun onEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.Login -> login(uiEvent.username, uiEvent.password)
            else -> {}
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val param = LoginUserParam(
                username = username,
                password = password
            )
            val result = loginUserUseCase(param)
            when (result) {
                is Result.Error<*> -> _uiState.update {
                    it.copy(
                        error = result.error
                    )
                }

                is Result.Success<User> -> {
                    result.data?.id?.let { userId ->
                        viewModelScope.launch {
                            _eventFlow.emit(LoginNavigationEvent.AuthSuccess(userId))
                        }
                    } ?: run {
                        _uiState.update {
                            it.copy(error = "Something went wrong")
                        }
                    }
                }
            }
        }
    }

    private fun fetchLastSignedInUser() {
        val username = getLastSignedInUserUseCase()
        username?.let {
            _uiState.update {
                it.copy(
                    lastSignedInUsername = username
                )
            }
        }
    }
}