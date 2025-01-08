package com.example.partyapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.partyapp.data.LocationDetails
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val eventId : Int = 0,

    @Embedded val creator: User,
    var name : String,
    var image : String,
    @Embedded var location: LocationDetails,
    @ColumnInfo(name = "starting_time") var starts : String,
    @ColumnInfo(name = "ending_time") var ends : String,
    var description: String,
    var day : Int,
    var participants: Long

)
