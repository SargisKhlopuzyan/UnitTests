package com.sargis.khlopuzyan.presenter.navigation

const val mainRoute = "main"

sealed class MainRoutes(val route: String) {
    object Main : AuthRoutes("$mainRoute/main")
}