package com.example.partyapp.di

import android.content.Context
import com.example.partyapp.PartyApp
import com.example.partyapp.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideSettingsRepository(@ApplicationContext context: Context) = SettingsRepository(context)
    @Singleton
    @Provides
    fun provideUserRepository(@ApplicationContext context: Context) =
        UserRepository((context.applicationContext as PartyApp).database.userDAO(), context)
    @Singleton
    @Provides
    fun provideEventRepository(@ApplicationContext context: Context) =
        EventRepository((context.applicationContext as PartyApp).database.eventDAO())
    @Singleton
    @Provides
    fun provideUserAddEventRepository(@ApplicationContext context: Context) =
        UserAddEventRepository((context.applicationContext as PartyApp).database.userAddEventDAO())
    @Singleton
    @Provides
    fun provideUserScansEventRepository(@ApplicationContext context: Context) =
        UserScansEventRepository((context.applicationContext as PartyApp).database.userScansEventDAO())
}