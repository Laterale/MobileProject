package com.example.partyapp.data.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.Relation
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User

@Entity(primaryKeys = ["id","eventId"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        ),
        ForeignKey(
            entity = Event::class,
            parentColumns = ["eventId"],
            childColumns = ["eventId"]
        )
    ]
)
data class UserScansEventCrossRef (
    val id : Int,
    val eventId : Int,
)

data class UserScansEvent(
    @Embedded val user: User,
    @Relation(
        parentColumn="id",
        entityColumn = "eventId",
        associateBy = Junction(UserAddEventCrossRef::class)
    )
    val events: List<Event>
)