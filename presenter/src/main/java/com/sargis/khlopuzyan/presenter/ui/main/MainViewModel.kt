package com.sargis.khlopuzyan.presenter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.DeleteUserUseCase
import com.sargis.khlopuzyan.domain.usecase.ObserveAllUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val observeAllUsersUseCase: ObserveAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<MainUiState>(MainUiState())

    val uiState: StateFlow<MainUiState> = _uiState.onStart {
        observeAllUser()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MainUiState()
    )

    fun onEvent(uiEvent: MainUiEvent) {
        when (uiEvent) {
            is MainUiEvent.DeleteUser -> deleteUser(uiEvent.user)
            is MainUiEvent.SignedOut -> {}
        }
    }

    private fun observeAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            observeAllUsersUseCase().collect { allUsers ->
                _uiState.update {
                    it.copy(
                        allUsers = allUsers
                    )
                }
            }
        }
    }

    private fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserUseCase(user)
        }
    }
}