package com.example.partyapp.data.repository

import androidx.annotation.WorkerThread
import com.example.partyapp.data.dao.UserCreateEventDAO
import com.example.partyapp.data.relation.UserCreateEventCrossRef
import kotlinx.coroutines.flow.Flow

class UserCreateEventRepository (private val userCreateEventDAO: UserCreateEventDAO){

    @WorkerThread
    suspend fun insertEvent(userCreateEventCrossRef: UserCreateEventCrossRef){
        userCreateEventDAO.insertEvent(userCreateEventCrossRef)
    }

    @WorkerThread
    fun getCreatorByEventId(eventId: Int): Flow<List<UserCreateEventCrossRef>> {
        return userCreateEventDAO.getCreatorByEventId(eventId)
    }

    @WorkerThread
    fun getEventByCreatorId(userId: Int): Flow<List<UserCreateEventCrossRef>> {
        return userCreateEventDAO.getEventsByCreatorId(userId)
    }
}
