package com.example.partyapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.partyapp.data.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDAO {
    @Transaction
    @Query("SELECT * FROM event")
    fun getEvents(): Flow<List<Event>>

    @Query("SELECT * FROM event WHERE eventId=:eventId")
    fun getEventByEventId(eventId: Int): Flow<List<Event>>

    @Query("SELECT * FROM event WHERE city=:filter")
    fun getEventsByCity(filter: String): Flow<List<Event>>

    @Query("UPDATE event SET participants=:newPref WHERE eventId=:eventId")
    suspend fun updateParticipants(newPref: Int, eventId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewEvent(vararg event: Event)

    @Query("DELETE FROM event WHERE eventId=:eventId")
    suspend fun deleteEvent(eventId: Int)
}