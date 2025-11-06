package com.sargis.khlopuzyan.presenter.navigation

const val authRoute = "auth"

sealed class AuthRoutes(val route: String) {
    object Splash : AuthRoutes("$authRoute/splash")
    object Login : AuthRoutes("$authRoute/login")
    object Register : AuthRoutes("$authRoute/register")
}