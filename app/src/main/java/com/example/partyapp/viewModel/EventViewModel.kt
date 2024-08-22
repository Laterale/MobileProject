package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    val allEvents = repository.allEvents

    fun getEventByEventId(eventId: Int): Flow<List<Event>> {
        return repository.getEventByEventId(eventId)
    }

    fun getEventsByCity(city: String): Flow<List<Event>> {
        return repository.getEventsByCity(city)
    }

    fun updateParticipants(newNum: Int, eventId: Int) = viewModelScope.launch {
        repository.updateParticipants(newNum, eventId)
    }
}