package com.example.partyapp.data.repository

import androidx.annotation.WorkerThread
import com.example.partyapp.data.dao.UserAddEventDAO
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserAddEventCrossRef
import kotlinx.coroutines.flow.Flow

class UserAddEventRepository (private val userAddEventDAO: UserAddEventDAO){

    val getAllParticipants: Flow<List<UserAddEventCrossRef>> = userAddEventDAO.getAllParticipants()

    @WorkerThread
    fun getParticipantsFromEventId(eventId: Int): Flow<List<User>> {
        return userAddEventDAO.getParticipantsFromEventId(eventId)
    }

    @WorkerThread
    suspend fun addParticipant(userAddEventCrossRef: UserAddEventCrossRef){
        userAddEventDAO.addParticipant(userAddEventCrossRef)
    }

    @WorkerThread
    suspend fun deleteParticipant(userAddEventCrossRef: UserAddEventCrossRef){
        userAddEventDAO.deleteParticipant(userAddEventCrossRef)
    }

}