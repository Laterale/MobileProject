package com.example.partyapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserScansEventCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserScansEventDAO {
    @Transaction
    @Query("SELECT * FROM UserScansEventCrossRef")
    fun getAllScans() : Flow<List<UserScansEventCrossRef>>

    @Query("SELECT e.* FROM Event e JOIN UserScansEventCrossRef use ON e.eventId = use.eventId WHERE use.id=:userId")
    fun getScansFromUserId(userId: Int) : Flow<List<Event>>

    @Query("SELECT e.* FROM event e JOIN userscanseventcrossref use ON e.eventId = use.eventId WHERE e.id=:creatorId")
    fun getNumberOfScansObtained(creatorId: Int) : Flow<List<Event>>

    @Query("SELECT u.* FROM User u JOIN UserScansEventCrossRef use ON u.id = use.id WHERE use.eventId=:eventId")
    fun getScansFromEventId(eventId: Int) : Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScan(vararg userScansEventCrossRef: UserScansEventCrossRef)
}