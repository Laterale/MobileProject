package com.example.partyapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserScansEventCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserScansEventDAO {
    @Transaction
    @Query("SELECT * FROM UserScansEventCrossRef")
    fun getAllScans() : Flow<List<UserScansEventCrossRef>>

    @Query("SELECT * FROM UserScansEventCrossRef WHERE id=:userId")
    fun getScansFromUserId(userId: Int) : Flow<List<UserScansEventCrossRef>>

    @Query("SELECT * FROM UserScansEventCrossRef WHERE id=:eventId")
    fun getScansFromEventId(eventId: Int) : Flow<List<UserScansEventCrossRef>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScan(vararg userScansEventCrossRef: UserScansEventCrossRef)
}