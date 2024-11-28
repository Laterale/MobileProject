package com.example.partyapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.partyapp.ui.components.PartyAppLogo


@Composable
fun LoadingScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    session: String
) {

    if (session != "default") {
        if (session == "") {
            navigateToLogin()
        } else {
            navigateToHome()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PartyAppLogo(color = Color.White)
        }
    }
}


