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

    fun createNewEvent(event: Event) {
        viewModelScope.launch {
            eRepository.insertNewEvent(event)
        }
    }

    fun deleteEvent(eventId: Int) {
        viewModelScope.launch {
            eRepository.deleteEvent(eventId)
        }
    }

// EVENT ADDED

    val allParticipants = uaeRepository.getAllParticipants

    fun getParticipantsFromEventId(eventId: Int) = uaeRepository.getParticipantsFromEventId(eventId)

    fun updateParticipants(newNum: Int, eventId: Int) = viewModelScope.launch {
        eRepository.updateParticipants(newNum, eventId)
    }

    fun addParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        uaeRepository.addParticipant(userAddEventCrossRef)
    }

    fun deleteParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        uaeRepository.deleteParticipant(userAddEventCrossRef)
    }

// EVENT SCANS

    val allScans = useRepository.allScans

    fun getScansFromUserId(userId: Int) = useRepository.getScansFromUserId(userId)

    fun getScansReceived(creatorId:Int) = useRepository.getNumberOfScansObtained(creatorId)

    fun getScansFromEventId(eventId: Int) = useRepository.getScansFromEventId(eventId)

    fun addScan(userScansEventCrossRef: UserScansEventCrossRef) = viewModelScope.launch {
        useRepository.addScan(userScansEventCrossRef)
    }


}