package com.sargis.khlopuzyan.presenter.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.domain.entity.User

@Composable
fun MainScreen(uiState: State<MainUiState>, onEvent: (MainUiEvent) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            item {
                Button(
                    onClick = {
                        onEvent(MainUiEvent.SignedOut)
                    }
                ) {
                    Text(text = "Signed Out")
                }
            }
            items(uiState.value.allUsers.size) {
                val user = uiState.value.allUsers[it]
                UserItem(user) {
                    onEvent(MainUiEvent.DeleteUser(user))
                }
            }
        }
    }
}

@Composable
private fun UserItem(user: User, onDeleteUserClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width(0.dp)
                .wrapContentHeight()
                .weight(1f)
        ) {
            Text(text = user.username)
            Spacer(Modifier.height(4.dp))
            Text(text = user.firstName)
            Spacer(Modifier.height(4.dp))
            Text(text = user.lastName)
        }

        Button(
            onClick = {
                onDeleteUserClicked()
            }
        ) {
            Text(text = "Delete")
        }
    }
}

@Preview
@Composable
private fun UserItemPreview() {
    val user = User(
        id = 2,
        firstName = "Sargis",
        lastName = "Khlopuzyan",
        username = "SargisKh2",
        password = "a12345"
    )
    UserItem(user) {

    }
}