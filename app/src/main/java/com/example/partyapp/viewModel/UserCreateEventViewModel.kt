package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserCreateEventCrossRef
import com.example.partyapp.data.repository.UserCreateEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserCreateEventViewModel @Inject constructor(
    private val repository: UserCreateEventRepository
) : ViewModel() {

    fun insertEvent(userCreateEventCrossRef: UserCreateEventCrossRef) = viewModelScope.launch {
        repository.insertEvent(userCreateEventCrossRef)
    }

    fun getCreatorByEventId(eventId: Int): Flow<List<UserCreateEventCrossRef>> {
        return repository.getCreatorByEventId(eventId)
    }

    fun getEventByCreatorId(userId: Int): Flow<List<UserCreateEventCrossRef>> {
        return repository.getEventByCreatorId(userId)
    }
}