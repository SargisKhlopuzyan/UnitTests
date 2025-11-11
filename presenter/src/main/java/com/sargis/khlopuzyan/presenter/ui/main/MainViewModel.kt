package com.sargis.khlopuzyan.presenter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.DeleteUserUseCase
import com.sargis.khlopuzyan.domain.usecase.GetLastSignedInUsernameUseCase
import com.sargis.khlopuzyan.domain.usecase.GetUserByUsernameUseCase
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
    private val lastSignedInUsernameUseCase: GetLastSignedInUsernameUseCase,
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val observeAllUsersUseCase: ObserveAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<MainUiState>(MainUiState())

    val uiState: StateFlow<MainUiState> = _uiState.onStart {
//        getSignedInUserAndObserveAllUser()
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

    private suspend fun getSignedInUserAndObserveAllUser() {
        getSignedInUser()
        observeAllUser()
    }

    private suspend fun getSignedInUser() {
        val lastSignedInUserName = lastSignedInUsernameUseCase()
        if (lastSignedInUserName != null) {
            val lastSignedInUser: User? = getUserByUsernameUseCase(lastSignedInUserName)
            lastSignedInUser?.let { lastSignedInUser ->
                _uiState.update {
                    it.copy(
                        firstName = lastSignedInUser.firstName,
                        lastName = lastSignedInUser.lastName,
                        username = lastSignedInUser.username
                    )
                }
            }
        }
    }

    private fun observeAllUser() {
        println("LOG_TAG *********** observeAllUser ***********")
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