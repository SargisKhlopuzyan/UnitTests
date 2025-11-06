package com.sargis.khlopuzyan.presenter.ui.auth.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sargis.khlopuzyan.presenter.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    LaunchedEffect(true) {
        delay(2000)

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}