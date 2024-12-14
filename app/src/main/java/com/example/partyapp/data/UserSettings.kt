package com.example.partyapp.data

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val useSystemTheme: Boolean,
    val customTheme: String
)