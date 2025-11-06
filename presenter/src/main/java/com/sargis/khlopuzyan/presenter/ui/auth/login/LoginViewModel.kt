package com.sargis.khlopuzyan.presenter.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.LoginUserParam
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.GetLastSignedInUserUseCase
import com.sargis.khlopuzyan.domain.usecase.LoginUserUseCase
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

    private var _navigationEvent: MutableSharedFlow<LoginNavigationEvent> = MutableSharedFlow()
    var navigationEvent: SharedFlow<LoginNavigationEvent> = _navigationEvent.asSharedFlow()


    fun onEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.Login -> login(uiEvent.userName, uiEvent.password)
            else -> {}
        }
    }

    fun login(userName: String, password: String) {
        val param = LoginUserParam(
            userName = userName,
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
                    _navigationEvent.tryEmit(LoginNavigationEvent.AuthSuccess(userId))
                } ?: run {
                    _uiState.update {
                        it.copy(error = "Something went wrong")
                    }
                }
            }
        }
    }

    fun fetchLastSignedInUser() {
        val userName = getLastSignedInUserUseCase()
        userName?.let {
            _uiState.update {
                it.copy(
                    lastSignedInUsername = userName
                )
            }
        }
    }
}