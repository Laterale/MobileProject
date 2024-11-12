package com.example.partyapp.data.repository;

import androidx.annotation.WorkerThread;
import com.example.partyapp.data.dao.UserCreateEventDAO;
import com.example.partyapp.data.relation.UserCreateEventCrossRef;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\f\u001a\u00020\nH\u0007J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/partyapp/data/repository/UserCreateEventRepository;", "", "userCreateEventDAO", "Lcom/example/partyapp/data/dao/UserCreateEventDAO;", "(Lcom/example/partyapp/data/dao/UserCreateEventDAO;)V", "getCreatorByEventId", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/relation/UserCreateEventCrossRef;", "eventId", "", "getEventByCreatorId", "userId", "insertEvent", "", "userCreateEventCrossRef", "(Lcom/example/partyapp/data/relation/UserCreateEventCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserCreateEventRepository {
    private final com.example.partyapp.data.dao.UserCreateEventDAO userCreateEventDAO = null;
    
    public UserCreateEventRepository(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.dao.UserCreateEventDAO userCreateEventDAO) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object insertEvent(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserCreateEventCrossRef userCreateEventCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserCreateEventCrossRef>> getCreatorByEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserCreateEventCrossRef>> getEventByCreatorId(int userId) {
        return null;
    }
}