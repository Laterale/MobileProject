package com.example.partyapp.di;

import android.content.Context;
import com.example.partyapp.PartyApp;
import com.example.partyapp.data.repository.*;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/example/partyapp/di/DataModule;", "", "()V", "provideEventRepository", "Lcom/example/partyapp/data/repository/EventRepository;", "context", "Landroid/content/Context;", "provideSettingsRepository", "Lcom/example/partyapp/data/repository/SettingsRepository;", "provideUserAddEventRepository", "Lcom/example/partyapp/data/repository/UserAddEventRepository;", "provideUserCreateEventRepository", "Lcom/example/partyapp/data/repository/UserCreateEventRepository;", "provideUserRepository", "Lcom/example/partyapp/data/repository/UserRepository;", "provideUserScansEventRepository", "Lcom/example/partyapp/data/repository/UserScansEventRepository;", "app_debug"})
@dagger.Module
public final class DataModule {
    @org.jetbrains.annotations.NotNull
    public static final com.example.partyapp.di.DataModule INSTANCE = null;
    
    private DataModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.SettingsRepository provideSettingsRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.UserRepository provideUserRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.EventRepository provideEventRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.UserAddEventRepository provideUserAddEventRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.UserCreateEventRepository provideUserCreateEventRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.partyapp.data.repository.UserScansEventRepository provideUserScansEventRepository(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
}