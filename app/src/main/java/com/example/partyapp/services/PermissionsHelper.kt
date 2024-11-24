package com.example.partyapp.services

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class PermissionsHelper(context: Context) {

    @Composable
    fun RequestLocationPermission(onPermissionGranted: () -> Unit) {
        var hasPermission by remember { mutableStateOf(false) } // Track permission state
        var permissionRequested by remember { mutableStateOf(false) }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                hasPermission = isGranted
                if (isGranted) {
                    onPermissionGranted()
                }
            }
        )
        // Trigger permission request only once
        LaunchedEffect(permissionRequested) {
            if (!permissionRequested) {
                permissionRequested = true
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

}