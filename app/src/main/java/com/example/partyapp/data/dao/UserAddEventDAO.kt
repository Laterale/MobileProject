package com.example.partyapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserAddEventCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAddEventDAO{
    @Transaction
    @Query("SELECT * FROM UserAddEventCrossRef")
    fun getAllParticipants(): Flow<List<UserAddEventCrossRef>>

    @Query("SELECT * FROM UserAddEventCrossRef WHERE eventId=:eventId")
    fun getParticipantsFromEventId(eventId: Int): Flow<List<UserAddEventCrossRef>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParticipant(vararg userAddEventCrossRef: UserAddEventCrossRef)

    @Delete
    suspend fun deleteParticipant(vararg userAddEventCrossRef: UserAddEventCrossRef)
}
