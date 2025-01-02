package com.example.partyapp.ui.components

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.ui.theme.Glass10
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationPickerDialogButton(
    text: String,
    onLocationPicked: (Address) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    TextButton(
        text = text,
        onClick = { showDialog = true }
    )
    if (showDialog) {
        PartyDialog(
            title = stringResource(id = R.string.choose_location),
            content = {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                    LocationPicker(onLocationPicked = { address ->
                        onLocationPicked(address)
                        showDialog = false
                    })
                }
            },
            onDismissRequest = { showDialog = false }
        )
    }
}

@Composable
fun LocationPicker(
    onLocationPicked: (Address) -> Unit
) {
    val context = LocalContext.current
    val geocoder = Geocoder(context)
    val zoom = 10f
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(37.7749, -122.4194), zoom
        )
    }
    Column (verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AddressSearch(
            modifier = Modifier.height(60.dp),
            onSearch = { address ->
                cameraPositionState.position = CameraPosition.fromLatLngZoom(
                    LatLng(address.latitude, address.longitude), zoom
                )
            }
        )
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)) {
            Map(cameraPositionState = cameraPositionState) { latLng ->
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, zoom)
            }
        }
        TextButton(
            text = stringResource(id = R.string.confirm),
            onClick = {
                val addresses: MutableList<Address>? = geocoder.getFromLocation(
                    cameraPositionState.position.target.latitude,
                    cameraPositionState.position.target.longitude,
                    1
                )
                if (!addresses.isNullOrEmpty()) {
                    onLocationPicked(addresses[0])
                }
            }
        )
    }
}

@Composable
private fun AddressSearch(
    onSearch: (Address) -> Unit,
    modifier: Modifier = Modifier
) {
    var address by remember { mutableStateOf("") }
    val context = LocalContext.current
    val geocoder = Geocoder(context)
    val errNotFound = stringResource(id = R.string.err_address_not_found)
    val errNoAddress = stringResource(id = R.string.err_no_address)
    Row (
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        PartyTextField(
            value = address, onValueChange = { address = it },
            placeholder = stringResource(id = R.string.lbl_city_address),
            keyboardImeAction = ImeAction.Done,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Glass10, shape = RoundedCornerShape(10.dp))
                .padding(5.dp),
            //colors = iconButtonColors(Glass10),
            onClick = {
                if (address.isNotBlank()) {
                    val addresses: MutableList<Address>? = geocoder.getFromLocationName(address, 1)
                    if (!addresses.isNullOrEmpty()) {
                        onSearch(addresses[0])
                    } else {
                        Toast.makeText(context, errNotFound, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, errNoAddress, Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = Color.White
            )
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
private fun Map(
    cameraPositionState: CameraPositionState,
    onLocationGet: (LatLng) -> Unit
) {
    val context = LocalContext.current
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true),
    ) {
        MapEffect { googleMap -> applyMapStyle(googleMap, context) }
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
            title = stringResource(id = R.string.you),
            snippet = stringResource(id = R.string.you_here)
        )
        GoogleMap.OnMapClickListener { latLng -> onLocationGet(latLng) }
    }
}

private fun applyMapStyle(googleMap: GoogleMap, context: Context) {
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