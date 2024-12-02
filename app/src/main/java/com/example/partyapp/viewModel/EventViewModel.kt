package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.relation.UserAddEventCrossRef
import com.example.partyapp.data.relation.UserScansEventCrossRef
import com.example.partyapp.data.repository.EventRepository
import com.example.partyapp.data.repository.UserAddEventRepository
import com.example.partyapp.data.repository.UserScansEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eRepository: EventRepository,
    private val useRepository: UserScansEventRepository,
    private val uaeRepository: UserAddEventRepository
) : ViewModel() {

    val events = eRepository.allEvents

    private var _eventSelected: Event? = null
    val eventSelected
        get() = _eventSelected
    fun selectEvent(event: Event){            //call this functions when navigating to an event screen
        _eventSelected = event
    }

    /*
    fun getEventByEventId(eventId: Int): Flow<List<Event>> {
        return eRepository.getEventByEventId(eventId)
    }
    fun getEventsByCity(city: String): Flow<List<Event>> {
        return eRepository.getEventsByCity(city)
    }
    */
    fun updateParticipants(newNum: Int, eventId: Int) = viewModelScope.launch {
        eRepository.updateParticipants(newNum, eventId)
    }

    val allParticipants = uaeRepository.getAllParticipants

    fun getParticipantsFromEventId(eventId: Int): Flow<List<UserAddEventCrossRef>> {
        return uaeRepository.getParticipantsFromEventId(eventId)
    }

    fun addParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        uaeRepository.addParticipant(userAddEventCrossRef)
    }

    fun deleteParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        uaeRepository.deleteParticipant(userAddEventCrossRef)
    }


    val allScans = useRepository.allScans

    fun getScansFromUserId(userId: Int) = viewModelScope.launch {
        useRepository.getScansFromUserId(userId)
    }

    fun getScansFromEventId(eventId: Int) = viewModelScope.launch {
        useRepository.getScansFromEventId(eventId)
    }

    fun addScan(userScansEventCrossRef: UserScansEventCrossRef) = viewModelScope.launch {
        useRepository.addScan(userScansEventCrossRef)
    }
}