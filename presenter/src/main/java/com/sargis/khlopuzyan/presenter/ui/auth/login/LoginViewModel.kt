package com.sargis.khlopuzyan.presenter.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.SaveUserParam
import com.sargis.khlopuzyan.domain.usecase.GetUserUseCase
import com.sargis.khlopuzyan.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<LoginUiState>(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState.onStart {
        loadUser()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginUiState()
    )

    fun onEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.Save -> save(uiEvent.firstName, uiEvent.lastName)
        }
    }

    fun save(firstName: String, lastName: String) {
        val param = SaveUserParam(firstName = firstName, lastName = lastName)
        val isSaved = saveUserUseCase(param = param)

    }

    fun loadUser() {
        val user = getUserUseCase()
        _uiState.update {
            it.copy(
                isSaved = true,
                user = user
            )
        }
    }
}