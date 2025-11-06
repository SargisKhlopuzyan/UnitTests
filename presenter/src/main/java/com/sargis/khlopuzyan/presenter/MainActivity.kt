package com.sargis.khlopuzyan.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presenter.navigation.AppNavigation
import com.sargis.khlopuzyan.presenter.ui.theme.UnitTestsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitTestsTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}