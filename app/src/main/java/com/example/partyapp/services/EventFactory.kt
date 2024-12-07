package com.example.partyapp.services

import android.os.Parcelable.Creator
import com.example.partyapp.data.LocationDetails
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User

class EventFactory {

    fun CreateEmpty(): Event {
        return Event(
            eventId = -1,
            creator = User(
                id = -1,
                username = "Undefined",
                password = "",
                email = "",
                pfp = "",
                exp = 0
            ),
            name = "No name",
            image = "",
            location = LocationDetails(
                latitude = 0.0, longitude = 0.0,
                state = "No location data",
                city = "No location data",
                street = "No location data"
            ),
            starts = "00:00",
            ends = "00:00",
            description = "No description",
            day = 0,
            participants = 0
        )
    }

    fun createEmptyEvent(creator: User): Event {
        return Event(
            eventId = -1,
            creator = creator,
            name = "MyParty",
            image = "",
            location = LocationDetails(
                latitude = 0.0, longitude = 0.0,
                state = "No location data",
                city = "No location data",
                street = "No location data"
            ),
            starts = "00:00",
            ends = "00:00",
            description = "Insert description",
            day = 1,
            participants = 0
        )
    }

}