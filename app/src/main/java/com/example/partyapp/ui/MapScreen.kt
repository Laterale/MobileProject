package com.example.partyapp.ui

import LocationHelper
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.services.EventUtilities
import com.example.partyapp.services.PermissionsHelper
import com.example.partyapp.ui.components.EventCard
import com.example.partyapp.ui.components.PartyDialog
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

private val SINGAPORE_LAT = 1.35
private val SINGAPORE_LONG = 103.87


@Composable
fun MapScreen(
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onEventMarkerClicked: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val singapore = LatLng(SINGAPORE_LAT, SINGAPORE_LONG)
    val context = LocalContext.current
    var location: Location? by remember { mutableStateOf(null) }

    PermissionsHelper(context).RequestLocationPermission {
        LocationHelper(context).getCurrentLocation { loc ->
            location = loc
        }
    }

    ShowMapCenteredOn(
        location = if (location != null) {
            LatLng(location!!.latitude, location!!.longitude)
        } else {
            singapore
        },
        eventViewModel = eventViewModel,
        userViewModel = userViewModel,
        onEventMarkerClicked = onEventMarkerClicked,
        settingsViewModel = settingsViewModel
    )
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun ShowMapCenteredOn(
    location: LatLng,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onEventMarkerClicked: () -> Unit = {},
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    var showDialog by remember { mutableStateOf(false) }
    MapUiSettings(zoomControlsEnabled = true)
    if (location.latitude != SINGAPORE_LAT && location.longitude != SINGAPORE_LONG) {
        LaunchedEffect(key1 = true) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(location, 15f, 0f, 0f)
                ),
                durationMs = 1000
            )
        }
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true),
    ) {
        MapEffect { googleMap -> applyMapStyle(googleMap, context) }
        Marker(
            state = MarkerState(position = location),
            title = stringResource(id = R.string.you),
            snippet = stringResource(id = R.string.you_here),
        )
        EventsMarkers(
            eventViewModel = eventViewModel,
            onInfoWindowClick = { showDialog = true },
            onInfoWindowClose = { showDialog = false },
            settingsViewModel = settingsViewModel
        )
    }
    if (showDialog) {
        PartyDialog(
            title = stringResource(id = R.string.lbl_event_details),
            content = {
                EventDetail(
                    eventViewModel = eventViewModel,
                    userViewModel = userViewModel,
                    onEventMarkerClicked = onEventMarkerClicked
                )
            },
            onDismissRequest = { showDialog = false }
        )
    }
}

@Composable
private fun EventsMarkers(
    eventViewModel: EventViewModel,
    onInfoWindowClick: () -> Unit = {},
    onInfoWindowClose: () -> Unit = {},
    settingsViewModel: SettingsViewModel
) {
    val filters = settingsViewModel.settings.collectAsState(initial = userSettings)
    val events = eventViewModel.events.collectAsState(initial = mutableListOf()).value
        .filter { e ->
            utilities.dayToString(e.day) == filters.value!!.dateFilter
        }
    events.forEach { event ->
        if (event.location.latitude == 0.0 && event.location.longitude == 0.0) return@forEach
        Marker(
            state = MarkerState(position = LatLng(event.location.latitude, event.location.longitude)),
            title = event.name,
            snippet = event.description,
            onInfoWindowClick = {
                eventViewModel.selectEvent(event)
                onInfoWindowClick()
            },
            onInfoWindowClose = { onInfoWindowClose() }
        )
    }
}

@Composable
private fun EventDetail(
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onEventMarkerClicked: () -> Unit = {},
) {
    val context = LocalContext.current
    val event = eventViewModel.eventSelected!!
    val dateStr = EventUtilities().getEventDateString(event)
    val address = EventUtilities().getEventLocationString(context, event)
    Column (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(bottom = 15.dp)
    ) {
        EventCard(
            onEventClicked = {
                onEventMarkerClicked()
            },//piccolo gnomo piccolo <3
            eventViewModel = eventViewModel,
            userViewModel = userViewModel,
            event = event,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = stringResource(id = R.string.lbl_event_day),
                tint = Color.White
            )
            Text(text = "${stringResource(id = R.string.`when`)}: $dateStr (${event.starts}-${event.ends})", color = Color.White)
        }
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Icon(
                imageVector = Icons.Filled.AddLocation,
                contentDescription = stringResource(id = R.string.lbl_event_location),
                tint = Color.White
            )
            Text(text = "${stringResource(id = R.string.where)}: $address", color = Color.White)
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
        Text(text = "${stringResource(id = R.string.description)}: ${event.description}", color = Color.White)
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
