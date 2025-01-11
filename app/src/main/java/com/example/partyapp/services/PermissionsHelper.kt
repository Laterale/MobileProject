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

    /**
     * @param permission The permission to request. Ex. `Manifest.permission.CAMERA`
     */
    @Composable
    fun RequestPermission(
        permission: String,
        onPermissionGranted: () -> Unit = {},
        onPermissionDenied: () -> Unit = {}
    ) {
        var hasPermission by remember { mutableStateOf(false) } // Track permission state
        var permissionRequested by remember { mutableStateOf(false) }

        val cameraPermissionRequest = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                hasPermission = isGranted
                if (isGranted) {
                    onPermissionGranted()
                }
                else {
                    onPermissionDenied()
                }
            }
        )
        // Trigger permission request only once
        LaunchedEffect(permissionRequested) {
            if (!permissionRequested) {
                permissionRequested = true
                cameraPermissionRequest.launch(permission)
            }
        }
    }

    @Composable
    fun RequestCameraPermission(
        doIfGranted: () -> Unit = {},
        elseIfDenied: () -> Unit = {}
    ) {
        RequestPermission(Manifest.permission.CAMERA, doIfGranted, elseIfDenied)
    }

    @Composable
    fun RequestLocationPermission(onPermissionGranted: () -> Unit) {
        RequestPermission(Manifest.permission.ACCESS_FINE_LOCATION, onPermissionGranted)
    }

}