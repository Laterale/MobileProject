package com.example.partyapp.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user", indices = [Index(value=["username","email"],unique=true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val username : String,
    val password : String,
    val email : String,
    var pfp : String,
    val exp : Long,
)