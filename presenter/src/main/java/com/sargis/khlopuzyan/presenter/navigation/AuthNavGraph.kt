package com.sargis.khlopuzyan.presenter.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginNavigationEvent
import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginScreen
import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginUiEvent
import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginViewModel
import com.sargis.khlopuzyan.presenter.ui.auth.register.RegisterNavigationEvent
import com.sargis.khlopuzyan.presenter.ui.auth.register.RegisterScreen
import com.sargis.khlopuzyan.presenter.ui.auth.register.RegisterViewModel
import com.sargis.khlopuzyan.presenter.ui.auth.splash.SplashScreen
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onAuthSuccess: (userId: Int) -> Unit,
) {
    navigation(startDestination = AuthRoutes.Splash.route, route = authRoute) {
        composable(route = AuthRoutes.Splash.route) {
            LaunchedEffect(true) {
                delay(2000)
                navController.navigate(AuthRoutes.Login.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }
            SplashScreen()
        }

        composable(route = AuthRoutes.Login.route) {
            val viewModel: LoginViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val navigationEvent by viewModel.eventFlow.collectAsStateWithLifecycle(
                initialValue = null,
                lifecycleOwner = LocalLifecycleOwner.current,
                minActiveState = Lifecycle.State.STARTED
            )

            LaunchedEffect(key1 = navigationEvent) {
                navigationEvent?.let { event ->
                    when (event) {
                        LoginNavigationEvent.NavigateUp -> navController.navigateUp()
                        is LoginNavigationEvent.AuthSuccess -> {
                            onAuthSuccess(event.userId)
                        }
                    }
                }
            }

            LoginScreen(uiState, onEvent = {
                when (it) {
                    LoginUiEvent.Register -> navController.navigate(AuthRoutes.Register.route)
                    is LoginUiEvent.Login -> viewModel::onEvent.invoke(it)
                }
            })
        }

        composable(route = AuthRoutes.Register.route) {
            val viewModel: RegisterViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val navigationEvent by viewModel.eventFlow.collectAsStateWithLifecycle(null)

            LaunchedEffect(key1 = navigationEvent) {
                navigationEvent?.let { event ->
                    when (event) {
                        RegisterNavigationEvent.NavigateUp -> navController.navigateUp()
                        is RegisterNavigationEvent.Registered -> onAuthSuccess(event.userId)
                    }
                }
            }

            RegisterScreen(uiState, onEvent = {
                viewModel::onEvent.invoke(it)
            })
        }
    }
}