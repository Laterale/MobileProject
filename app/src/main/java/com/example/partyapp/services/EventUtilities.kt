package com.example.partyapp.services

import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
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
}