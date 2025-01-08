package com.example.partyapp.services

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import java.time.ZoneId
import java.util.Calendar

class EventUtilities {
    val BASE_DATE_INT = 1000_00_00

    fun getEventDateTime(event: Event): Calendar {
        val calendar = Calendar.getInstance()
        val (h, m) = event.starts.split(":").map { it.toInt() }
        calendar.set(Calendar.HOUR_OF_DAY, h)
        calendar.set(Calendar.MINUTE, m)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.DAY_OF_MONTH, event.day.mod(100))
        calendar.set(Calendar.MONTH, (event.day / 100).mod(100))
        calendar.set(Calendar.YEAR, event.day / 10000)
        return calendar
    }

    fun getEventDateString(event: Event): String {
        val date = getEventDateTime(event)
        return date.time.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .toString()
    }

    fun getEventLocationString(context: Context, event: Event): String {
        try {
            val geocoder = Geocoder(context)
            val addresses: MutableList<Address>? = geocoder.getFromLocation(event.location.latitude, event.location.longitude, 1)
            if (event.location.city != "" && !addresses.isNullOrEmpty()) {
                return addresses[0].getAddressLine(0)
            }
        } catch (_: Exception) { } // Throws exception if offline
        return event.location.city
    }
    
    fun isEventCreatedBy(event: Event, user: User): Boolean {
        return event.creator.username == user.username
    }
    
    fun isNewEvent(event: Event): Boolean {
        return event.eventId == -1
    }
    
    fun checkEventValid(event: Event) {
        if (event.name.isEmpty()) {
            throw IllegalStateException("Event name cannot be empty")
        }
        if (event.day < BASE_DATE_INT) {
            throw IllegalStateException("Invalid event date")
        }
        if (EventUtilities().getEventDateTime(event).before(Calendar.getInstance())) {
            throw IllegalStateException("Event date cannot be in the past")
        }
        if (event.location.city.isEmpty()) {
            throw IllegalStateException("Event location not set")
        }
    }

    fun dayToString(value: Int ): String {
        var day = (value + 100).toString()
        val stringDay = "" +
                day.get(0) +
                day.get(1) +
                day.get(2) +
                day.get(3) +
                "-" +
                day.get(4) +
                day.get(5) +
                "-" +
                day.get(6) +
                day.get(7)
        return stringDay
    }

    fun dateToStr(date: Calendar): String {
        return date.time.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .toString()
    }
}