package com.example.partyapp.data

import kotlinx.serialization.Serializable
import java.util.Calendar

@Serializable
data class UserSettings(
    val useSystemTheme: Boolean,
    val customTheme: String,
    val rangeFilter: Int,
    val dateFilter: String
)