package com.example.partyapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.partyapp.data.relation.UserCreateEventCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCreateEventDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(vararg userCreateEventCrossRef: UserCreateEventCrossRef)

    @Query("SELECT * FROM UserCreateEventCrossRef WHERE eventId=:eventId")
    fun getCreatorByEventId(eventId: Int): Flow<List<UserCreateEventCrossRef>>

    @Query("SELECT * FROM UserCreateEventCrossRef WHERE id=:userId")
    fun getEventsByCreatorId(userId: Int): Flow<List<UserCreateEventCrossRef>>
}