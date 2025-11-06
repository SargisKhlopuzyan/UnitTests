package com.sargis.khlopuzyan.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = authRoute
    ) {
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {
                navHostController.navigate(AuthRoutes.Login)
            }
        )
    }
}