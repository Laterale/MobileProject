package com.example.partyapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.UserViewModel

@Composable
fun SettingsScreen(
    navigateToLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(30.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoutButton(navigateToLogin, userViewModel)
    }
}

@Composable
fun LogoutButton(
    navigateToLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Glass10,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, Glass20),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            userViewModel.clearSession()
            navigateToLogin()
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Logout", style = Typography.bodyMedium)
    }
}
