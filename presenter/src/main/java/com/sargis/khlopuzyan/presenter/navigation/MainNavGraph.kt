package com.sargis.khlopuzyan.presenter.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presenter.ui.main.MainScreen
import com.sargis.khlopuzyan.presenter.ui.main.MainUiEvent
import com.sargis.khlopuzyan.presenter.ui.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    onSignedOut: () -> Unit,
) {
    navigation(startDestination = MainRoutes.Main.route, route = mainRoute) {
        composable(route = MainRoutes.Main.route) {
            val viewModel = koinViewModel<MainViewModel>()
            val usState = viewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(usState, onEvent = {
                when(it) {
                    MainUiEvent.SignedOut -> onSignedOut()
                }
            })
        }
    }
}