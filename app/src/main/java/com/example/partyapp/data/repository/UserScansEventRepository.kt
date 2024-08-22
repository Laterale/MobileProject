package com.example.partyapp.data.repository

import androidx.annotation.WorkerThread
import com.example.partyapp.data.dao.UserScansEventDAO
import com.example.partyapp.data.relation.UserScansEventCrossRef
import kotlinx.coroutines.flow.Flow

class UserScansEventRepository (private val userScansEventDAO: UserScansEventDAO){
    val allScans: Flow<List<UserScansEventCrossRef>> = userScansEventDAO.getAllScans()

    @WorkerThread
    fun getScansFromUserId(userId: Int): Flow<List<UserScansEventCrossRef>> {
        return userScansEventDAO.getScansFromUserId(userId)
    }

    @WorkerThread
    fun getScansFromEventId(eventId: Int): Flow<List<UserScansEventCrossRef>> {
        return userScansEventDAO.getScansFromEventId(eventId)
    }

    @WorkerThread
    suspend fun addScan(userScansEventCrossRef: UserScansEventCrossRef){
        userScansEventDAO.addScan(userScansEventCrossRef)
    }
}