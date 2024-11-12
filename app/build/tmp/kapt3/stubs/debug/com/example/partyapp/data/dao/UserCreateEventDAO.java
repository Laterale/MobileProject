package com.example.partyapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.partyapp.data.relation.UserCreateEventCrossRef;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\t\u001a\u00020\u0007H\'J%\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\r\"\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/partyapp/data/dao/UserCreateEventDAO;", "", "getCreatorByEventId", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/relation/UserCreateEventCrossRef;", "eventId", "", "getEventsByCreatorId", "userId", "insertEvent", "", "userCreateEventCrossRef", "", "([Lcom/example/partyapp/data/relation/UserCreateEventCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserCreateEventDAO {
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 5)
    public abstract java.lang.Object insertEvent(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserCreateEventCrossRef[] userCreateEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserCreateEventCrossRef WHERE eventId=:eventId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserCreateEventCrossRef>> getCreatorByEventId(int eventId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserCreateEventCrossRef WHERE id=:userId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserCreateEventCrossRef>> getEventsByCreatorId(int userId);
}