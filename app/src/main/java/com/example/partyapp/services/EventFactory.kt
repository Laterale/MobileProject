package com.example.partyapp.services

import com.example.partyapp.data.LocationDetails
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User

class EventFactory {

    fun createEmpty(): Event {
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
            name = "",
            image = "",
            location = LocationDetails(
                latitude = 0.0, longitude = 0.0,
                state = "No location data",
                city = "No location data",
                street = "No location data"
            ),
            starts = "00:00",
            ends = "00:00",
            description = "",
            day = 1,
            participants = 0
        )
    }

}