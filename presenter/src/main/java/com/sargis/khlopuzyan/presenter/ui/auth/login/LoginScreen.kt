package com.sargis.khlopuzyan.presenter.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var username by rememberSaveable {
                mutableStateOf(uiState.lastSignedInUsername ?: "")
            }

            LaunchedEffect(uiState.lastSignedInUsername) {
                username = uiState.lastSignedInUsername ?: ""
            }

            var password by rememberSaveable {
                mutableStateOf("")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text("User name")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Password")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(
                    onClick = {
                        onEvent(LoginUiEvent.Login(username, password))
                    }
                ) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        onEvent(LoginUiEvent.Register)
                    }
                ) {
                    Text(text = "Register")
                }
            }

            if (!uiState.error.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = uiState.error,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(LoginUiState()) {

    }
}