package com.example.partyapp.data.repository

import androidx.annotation.WorkerThread
import com.example.partyapp.data.dao.EventDAO
import com.example.partyapp.data.entity.Event
import kotlinx.coroutines.flow.Flow

class EventRepository (private val eventDAO: EventDAO){
    val allEvents: Flow<List<Event>> = eventDAO.getEvents()

    @WorkerThread
    fun getEventByEventId(eventId: Int): Flow<List<Event>> {
        return eventDAO.getEventByEventId(eventId)
    }

    @WorkerThread
    fun getEventsByCity(city: String): Flow<List<Event>> {
        return eventDAO.getEventsByCity(city)
    }

    @WorkerThread
    suspend fun updateParticipants(newNum: Int, eventId: Int){
        eventDAO.updateParticipants(newNum, eventId)
    }

    @WorkerThread
    suspend fun insertNewEvent(event: Event){
        eventDAO.insertNewEvent(event)
    }
}