package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.relation.UserCreateEventCrossRef
import com.example.partyapp.data.repository.UserCreateEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserCreateEventViewModel @Inject constructor(
    private val repository: UserCreateEventRepository
) : ViewModel() {

    fun insertEvent(userCreateEventCrossRef: UserCreateEventCrossRef) = viewModelScope.launch {
        repository.insertEvent(userCreateEventCrossRef)
    }

    fun getCreatorByEventId(eventId: Int) = viewModelScope.launch {
        repository.getCreatorByEventId(eventId)
    }

    fun getEventByCreatorId(userId: Int) = viewModelScope.launch {
        repository.getEventByCreatorId(userId)
    }
}