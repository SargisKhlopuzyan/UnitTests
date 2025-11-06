package com.sargis.khlopuzyan.presenter.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun RegisterScreen(uiState: RegisterUiState, onEvent: (RegisterUiEvent) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var firstName by rememberSaveable {
                mutableStateOf("")
            }

            var lastName by rememberSaveable {
                mutableStateOf("")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = {
                    Text("First name")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = {
                    Text("Last name")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onEvent(RegisterUiEvent.Register(firstName, lastName))
                }
            ) {
                Text(text = "Register")
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
fun RegisterScreenPreview() {
    RegisterScreen(RegisterUiState(error = "User already exist")) {

    }
}