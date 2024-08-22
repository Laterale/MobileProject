package com.example.partyapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyapp.data.LocationDetails

class LocationViewModel: ViewModel() {


    private var _location: MutableState<LocationDetails> = mutableStateOf(LocationDetails(91.0,181.0, "Italy", "Forli", "Via del Canale 1"))

    val location
        get() = _location


    fun updateLocation(newLocation: LocationDetails){
        _location.value  = newLocation
    }
}