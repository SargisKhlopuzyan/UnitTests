package com.sargis.khlopuzyan.presenter.ui.auth.login

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            var firstName by remember {
                mutableStateOf(uiState.user?.firstName ?: "")
            }

            var lastName by remember {
                mutableStateOf(uiState.user?.lastName ?: "")
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
                    onEvent(LoginUiEvent.Save(firstName, lastName))
                }
            ) {
                Text(text = "Save")
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