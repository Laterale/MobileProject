package com.example.partyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.relation.UserScansEventCrossRef
import com.example.partyapp.data.repository.UserScansEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserScansEventViewModel @Inject constructor(
    private val repository: UserScansEventRepository
) : ViewModel() {

    val allScans = repository.allScans

    fun getScansFromUserId(userId: Int) = viewModelScope.launch {
        repository.getScansFromUserId(userId)
    }

    fun getScansFromEventId(eventId: Int) = viewModelScope.launch {
        repository.getScansFromEventId(eventId)
    }

    fun addScan(userScansEventCrossRef: UserScansEventCrossRef) = viewModelScope.launch {
        repository.addScan(userScansEventCrossRef)
    }


}