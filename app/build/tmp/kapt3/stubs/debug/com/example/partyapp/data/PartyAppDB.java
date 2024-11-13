package com.example.partyapp.data;

import android.content.Context;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.partyapp.data.dao.EventDAO;
import com.example.partyapp.data.dao.UserAddEventDAO;
import com.example.partyapp.data.dao.UserCreateEventDAO;
import com.example.partyapp.data.dao.UserDAO;
import com.example.partyapp.data.dao.UserScansEventDAO;
import com.example.partyapp.data.entity.Event;
import com.example.partyapp.data.entity.User;
import com.example.partyapp.data.relation.UserAddEventCrossRef;
import com.example.partyapp.data.relation.UserCreateEventCrossRef;
import com.example.partyapp.data.relation.UserScansEventCrossRef;

@androidx.room.Database(entities = {com.example.partyapp.data.entity.User.class, com.example.partyapp.data.entity.Event.class, com.example.partyapp.data.relation.UserAddEventCrossRef.class, com.example.partyapp.data.relation.UserCreateEventCrossRef.class, com.example.partyapp.data.relation.UserScansEventCrossRef.class}, version = 2, exportSchema = true)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/example/partyapp/data/PartyAppDB;", "Landroidx/room/RoomDatabase;", "()V", "eventDAO", "Lcom/example/partyapp/data/dao/EventDAO;", "userAddEventDAO", "Lcom/example/partyapp/data/dao/UserAddEventDAO;", "userCreateEventDAO", "Lcom/example/partyapp/data/dao/UserCreateEventDAO;", "userDAO", "Lcom/example/partyapp/data/dao/UserDAO;", "userScansEventDAO", "Lcom/example/partyapp/data/dao/UserScansEventDAO;", "Companion", "app_debug"})
public abstract class PartyAppDB extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull
    public static final com.example.partyapp.data.PartyAppDB.Companion Companion = null;
    @kotlin.jvm.Volatile
    private static volatile com.example.partyapp.data.PartyAppDB INSTANCE;
    
    public PartyAppDB() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.partyapp.data.dao.EventDAO eventDAO();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.partyapp.data.dao.UserDAO userDAO();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.partyapp.data.dao.UserAddEventDAO userAddEventDAO();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.partyapp.data.dao.UserCreateEventDAO userCreateEventDAO();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.partyapp.data.dao.UserScansEventDAO userScansEventDAO();
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/partyapp/data/PartyAppDB$Companion;", "", "()V", "INSTANCE", "Lcom/example/partyapp/data/PartyAppDB;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.partyapp.data.PartyAppDB getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}