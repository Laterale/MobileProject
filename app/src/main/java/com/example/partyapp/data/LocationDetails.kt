package com.example.partyapp.data

import kotlinx.serialization.Serializable

@Serializable
data class LocationDetails(
    val latitude : Double,
    val longitude : Double,
    val state : String,
    val city : String,
    val street : String
)