package com.example.partyapp.services

import android.os.Parcelable.Creator
import com.example.partyapp.data.LocationDetails
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User

class EventFactory {

    fun createEmptyEvent(creator: User): Event {
        return Event(
            eventId = -1,
            creator = creator,
            name = "MyParty",
            image = "",
            location = LocationDetails(
                latitude = 0.0, longitude = 0.0,
                state = "", city = "", street = ""
            ),
            starts = "",
            ends = "",
            description = "Insert description",
            day = 1,
            participants = 0
        )
    }

}