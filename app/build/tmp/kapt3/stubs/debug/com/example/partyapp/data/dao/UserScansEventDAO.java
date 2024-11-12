package com.example.partyapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.partyapp.data.entity.User;
import com.example.partyapp.data.relation.UserScansEventCrossRef;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\tH\'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\t2\u0006\u0010\f\u001a\u00020\rH\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\t2\u0006\u0010\u000f\u001a\u00020\rH\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/partyapp/data/dao/UserScansEventDAO;", "", "addScan", "", "userScansEventCrossRef", "", "Lcom/example/partyapp/data/relation/UserScansEventCrossRef;", "([Lcom/example/partyapp/data/relation/UserScansEventCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllScans", "Lkotlinx/coroutines/flow/Flow;", "", "getScansFromEventId", "eventId", "", "getScansFromUserId", "userId", "app_debug"})
public abstract interface UserScansEventDAO {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserScansEventCrossRef")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserScansEventCrossRef>> getAllScans();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserScansEventCrossRef WHERE id=:userId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserScansEventCrossRef>> getScansFromUserId(int userId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM UserScansEventCrossRef WHERE id=:eventId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserScansEventCrossRef>> getScansFromEventId(int eventId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 5)
    public abstract java.lang.Object addScan(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserScansEventCrossRef[] userScansEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}