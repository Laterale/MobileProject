package com.example.partyapp.ui

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.partyapp.services.PermissionsHelper
import com.example.partyapp.ui.components.PartyAppLogo


@Composable
fun LoadingScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    session: String
) {
    val context = LocalContext.current
    val permissionsHelper = PermissionsHelper(context)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissionsHelper.RequestPermission(Manifest.permission.POST_NOTIFICATIONS)
    }
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


