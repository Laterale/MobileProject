package com.example.partyapp.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value=["username","email"],unique=true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val name: String,
    val surname : String,
    val username : String,
    val password : String,
    val email : String,
    var pfp : String,
    val exp : Long
)