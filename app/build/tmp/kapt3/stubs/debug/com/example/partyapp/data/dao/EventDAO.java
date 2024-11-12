package com.example.partyapp.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.partyapp.data.entity.Event;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\n\u001a\u00020\u000bH\'J!\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/partyapp/data/dao/EventDAO;", "", "getEventByEventId", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/entity/Event;", "eventId", "", "getEvents", "getEventsByCity", "filter", "", "updateParticipants", "", "newPref", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface EventDAO {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM event")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getEvents();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM event WHERE eventId=:eventId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getEventByEventId(int eventId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM event WHERE city=:filter")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getEventsByCity(@org.jetbrains.annotations.NotNull
    java.lang.String filter);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE event SET participants=:newPref WHERE eventId=:eventId")
    public abstract java.lang.Object updateParticipants(int newPref, int eventId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}