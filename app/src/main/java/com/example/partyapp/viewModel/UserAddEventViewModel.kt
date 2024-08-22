package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.relation.UserAddEventCrossRef
import com.example.partyapp.data.repository.UserAddEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserAddEventViewModel @Inject constructor(
    private val repository: UserAddEventRepository
) : ViewModel() {

    val allParticipants = repository.getAllParticipants

    fun getParticipantsFromEventId(eventId: Int) = viewModelScope.launch {
        repository.getParticipantsFromEventId(eventId)
    }

    fun addParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        repository.addParticipant(userAddEventCrossRef)
    }

    fun deleteParticipant(userAddEventCrossRef: UserAddEventCrossRef) = viewModelScope.launch {
        repository.deleteParticipant(userAddEventCrossRef)
    }
}