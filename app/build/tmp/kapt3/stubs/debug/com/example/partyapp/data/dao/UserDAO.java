package com.example.partyapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.partyapp.data.entity.User;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ \u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\'J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\'J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00120\fH\'J%\u0010\u0013\u001a\u00020\u00032\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0015\"\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/partyapp/data/dao/UserDAO;", "", "changePfpFromId", "", "userId", "", "newPfp", "", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changeUsernameFromId", "newUsername", "checkLoginCredentials", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/partyapp/data/entity/User;", "username", "password", "getUserFromUsername", "getUsers", "", "insert", "user", "", "([Lcom/example/partyapp/data/entity/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateExpFromId", "newExp", "app_debug"})
public abstract interface UserDAO {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM user")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.User>> getUsers();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM user WHERE username=:username AND password=:password")
    public abstract kotlinx.coroutines.flow.Flow<com.example.partyapp.data.entity.User> checkLoginCredentials(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM user WHERE username=:username")
    public abstract com.example.partyapp.data.entity.User getUserFromUsername(@org.jetbrains.annotations.NotNull
    java.lang.String username);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE user SET username=:newUsername WHERE id=:userId")
    public abstract java.lang.Object changeUsernameFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newUsername, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE user SET pfp=:newPfp WHERE id=:userId")
    public abstract java.lang.Object changePfpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newPfp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE user SET exp=:newExp WHERE id=:userId")
    public abstract java.lang.Object updateExpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newExp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 5)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User[] user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}