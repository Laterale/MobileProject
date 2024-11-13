package com.example.partyapp.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.partyapp.data.entity.User;
import com.example.partyapp.data.relation.UserAddEventCrossRef;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J%\u0010\b\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b0\nH\'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eH\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/partyapp/data/dao/UserAddEventDAO;", "", "addParticipant", "", "userAddEventCrossRef", "", "Lcom/example/partyapp/data/relation/UserAddEventCrossRef;", "([Lcom/example/partyapp/data/relation/UserAddEventCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteParticipant", "getAllParticipants", "Lkotlinx/coroutines/flow/Flow;", "", "getParticipantsFromEventId", "eventId", "", "app_debug"})
public abstract interface UserAddEventDAO {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserAddEventCrossRef")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getAllParticipants();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserAddEventCrossRef WHERE eventId=:eventId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getParticipantsFromEventId(int eventId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 5)
    public abstract java.lang.Object addParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef[] userAddEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef[] userAddEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}