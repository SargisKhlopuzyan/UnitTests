package com.sargis.khlopuzyan.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(navHostController: NavHostController) {
    var starDestination = authRoute
    NavHost(
        navController = navHostController,
        startDestination = authRoute
    ) {
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {
                navHostController.navigate(MainRoutes.Main.route) {
                    navHostController.graph.findNode(starDestination)?.id?.let { starDestination ->
                        popUpTo(starDestination) {
                            inclusive = true
                        }
                    }
                    starDestination = mainRoute
                }
            }
        )

        mainNavGraph(
            navController = navHostController,
            onSignedOut = {
                navHostController.navigate(AuthRoutes.Login.route) {
                    navHostController.graph.findNode(starDestination)?.id?.let { starDestination ->
                        popUpTo(starDestination) {
                            inclusive = true
                        }
                    }
                    starDestination = authRoute
                }
            }
        )
    }
}