package com.example.partyapp.ui

import LocationHelper
import android.Manifest
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87)
    val context = LocalContext.current
    var location: Location? by remember { mutableStateOf(null) }

    RequestLocationPermission {
        LocationHelper(context).getCurrentLocation { loc ->
            location = loc
        }
    }

    if (location != null) {
        ShowMapCenteredOn(location = LatLng(location!!.latitude, location!!.longitude))
    } else {
        ShowMapCenteredOn(location = singapore)
    }
}

@Composable
fun ShowMapCenteredOn(location: LatLng) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    MapUiSettings(zoomControlsEnabled = true)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        Marker(
            state = MarkerState(position = location),
            title = "You",
            snippet = "You are here."
        )
    }
}

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