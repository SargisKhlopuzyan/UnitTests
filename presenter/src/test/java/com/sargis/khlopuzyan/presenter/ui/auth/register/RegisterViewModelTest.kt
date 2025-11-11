package com.sargis.khlopuzyan.presenter.ui.auth.register

import com.sargis.khlopuzyan.domain.entity.User
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.util.Result
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset

class RegisterViewModelTest {

    private val registerUserUseCase: RegisterUserUseCase = mock()
    private lateinit var registerViewModel: RegisterViewModel

    @Before
    fun beforeEach() {
        registerViewModel = RegisterViewModel(registerUserUseCase)
    }

    @After
    fun afterEach() {
        reset(registerUserUseCase)
    }

    @Test
    fun `after registering new user should not show ui error`() = runTest {
//        val registerViewModel = RegisterViewModel(registerUserUseCase)

        val firstName = "Sargis"
        val lastName = "Khlopuzyan"
        val username = "SargisKh"
        val password = "a1234"

        val user = User(1, firstName, lastName, username, password)

        `when`(registerUserUseCase(any())).thenReturn(Result.Success(user))

        val uiEvent = RegisterUiEvent.Register(
            firstName,
            lastName,
            username,
            password
        )
        registerViewModel.onEvent(uiEvent)

        val actual = registerViewModel.uiState.value
        Assertions.assertTrue(actual.error.isNullOrBlank())
    }

    @Test
    fun `registering new user Registered navigation event should be triggered`() = runTest {
//        val registerViewModel = RegisterViewModel(registerUserUseCase)

        val firstName = "Sargis"
        val lastName = "Khlopuzyan"
        val username = "SargisKh"
        val password = "a1234"

        val user = User(1, firstName, lastName, username, password)

        `when`(registerUserUseCase(any())).thenReturn(Result.Success(user))

        val uiEvent = RegisterUiEvent.Register(
            firstName,
            lastName,
            username,
            password
        )
        registerViewModel.onEvent(uiEvent)

        val navigationEvent: RegisterNavigationEvent? = registerViewModel.eventFlow.firstOrNull()

        Assertions.assertInstanceOf(RegisterNavigationEvent.Registered::class.java, navigationEvent)
    }

    @Test
    fun `registering new user should call registerUserUseCase one time`() = runTest {
//        val registerViewModel = RegisterViewModel(registerUserUseCase)

        val firstName = "Sargis"
        val lastName = "Khlopuzyan"
        val username = "SargisKh"
        val password = "a1234"

        val user = User(1, firstName, lastName, username, password)

        `when`(registerUserUseCase(any())).thenReturn(Result.Success(user))

        val uiEvent = RegisterUiEvent.Register(
            firstName,
            lastName,
            username,
            password
        )
        registerViewModel.onEvent(uiEvent)
        Mockito.verify(registerUserUseCase, Mockito.times(1)).invoke(any())
    }
}