package com.example.partyapp.data.repository;

import androidx.annotation.WorkerThread;
import com.example.partyapp.data.dao.UserAddEventDAO;
import com.example.partyapp.data.relation.UserAddEventCrossRef;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/example/partyapp/data/repository/UserAddEventRepository;", "", "userAddEventDAO", "Lcom/example/partyapp/data/dao/UserAddEventDAO;", "(Lcom/example/partyapp/data/dao/UserAddEventDAO;)V", "getAllParticipants", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/relation/UserAddEventCrossRef;", "getGetAllParticipants", "()Lkotlinx/coroutines/flow/Flow;", "addParticipant", "", "userAddEventCrossRef", "(Lcom/example/partyapp/data/relation/UserAddEventCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteParticipant", "getParticipantsFromEventId", "eventId", "", "app_debug"})
public final class UserAddEventRepository {
    private final com.example.partyapp.data.dao.UserAddEventDAO userAddEventDAO = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getAllParticipants = null;
    
    public UserAddEventRepository(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.dao.UserAddEventDAO userAddEventDAO) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getGetAllParticipants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getParticipantsFromEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object addParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef userAddEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object deleteParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef userAddEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}