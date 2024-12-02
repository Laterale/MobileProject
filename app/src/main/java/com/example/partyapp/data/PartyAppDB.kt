package com.example.partyapp.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.partyapp.data.dao.EventDAO
import com.example.partyapp.data.dao.UserAddEventDAO
import com.example.partyapp.data.dao.UserDAO
import com.example.partyapp.data.dao.UserScansEventDAO
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserAddEventCrossRef
import com.example.partyapp.data.relation.UserScansEventCrossRef

@Database(entities = [User::class, Event::class, UserAddEventCrossRef::class,UserScansEventCrossRef::class],
    version = 2,
    exportSchema = true)
abstract class PartyAppDB : RoomDatabase(){

    abstract fun eventDAO(): EventDAO
    abstract fun userDAO(): UserDAO
    abstract fun userAddEventDAO(): UserAddEventDAO
    abstract fun userScansEventDAO(): UserScansEventDAO

    companion object{
        @Volatile
        private var INSTANCE: PartyAppDB ?= null

        fun getDatabase(context: Context): PartyAppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PartyAppDB::class.java,
                    "app_database"
                )//.createFromAsset("database/app.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}