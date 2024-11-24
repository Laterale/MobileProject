package com.example.partyapp.ui

import LocationHelper
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.partyapp.R
import com.example.partyapp.services.PermissionsHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87)
    val context = LocalContext.current
    var location: Location? by remember { mutableStateOf(null) }

    PermissionsHelper(context).RequestLocationPermission {
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
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    MapUiSettings(zoomControlsEnabled = true)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true),
    ) {
        MapEffect { googleMap ->
            applyMapStyle(googleMap, context)
        }
        Marker(
            state = MarkerState(position = location),
            title = "You",
            snippet = "You are here."
        )
    }
}

fun applyMapStyle(googleMap: GoogleMap, context: Context) {
    try {
        val success = googleMap.setMapStyle(
            //Created with google maps styling wizard online
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
        )
        if (!success) {
            Log.e("MapStyle", "Style parsing failed.")
        }
    } catch (e: Exception) {
        Log.e("MapStyle", "Error loading map style: ${e.localizedMessage}")
    }
}
