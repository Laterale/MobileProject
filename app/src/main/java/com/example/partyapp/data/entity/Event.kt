package com.example.partyapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import com.example.partyapp.data.LocationDetails

@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val eventId : Int = 0,

    val name : String,
    val image : String,
    @Embedded val location: LocationDetails,
    @ColumnInfo(name = "starting_time") val starts : String,
    @ColumnInfo(name = "ending_time") val ends : String,
    val description: String,
    val slots : Long,
    val participants: Long,
)
