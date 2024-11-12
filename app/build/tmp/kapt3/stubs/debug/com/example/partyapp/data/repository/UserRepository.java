package com.example.partyapp.data.repository;

import android.content.Context;
import androidx.annotation.WorkerThread;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.example.partyapp.data.dao.UserDAO;
import com.example.partyapp.data.entity.User;
import kotlinx.coroutines.flow.Flow;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\tH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J \u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\b2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0007J\u0011\u0010\u001b\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\tH\u0007J\u0019\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u000eH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J!\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\tH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/example/partyapp/data/repository/UserRepository;", "", "userDAO", "Lcom/example/partyapp/data/dao/UserDAO;", "context", "Landroid/content/Context;", "(Lcom/example/partyapp/data/dao/UserDAO;Landroid/content/Context;)V", "preferenceFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getPreferenceFlow", "()Lkotlinx/coroutines/flow/Flow;", "users", "", "Lcom/example/partyapp/data/entity/User;", "getUsers", "changePfpFromId", "", "userId", "", "newPfp", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changeUsernameFromId", "newUsername", "checkLoginCredentials", "username", "password", "clearDataStore", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserFromUsername", "insertNewUser", "user", "(Lcom/example/partyapp/data/entity/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveToDataStore", "updateExpFromId", "newExp", "Companion", "app_debug"})
public final class UserRepository {
    private final com.example.partyapp.data.dao.UserDAO userDAO = null;
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.User>> users = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.partyapp.data.repository.UserRepository.Companion Companion = null;
    private static final kotlin.properties.ReadOnlyProperty dataStore$delegate = null;
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> SESSION_INFO = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.String> preferenceFlow = null;
    
    public UserRepository(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.dao.UserDAO userDAO, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.User>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getPreferenceFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveToDataStore(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object clearDataStore(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    public final kotlinx.coroutines.flow.Flow<com.example.partyapp.data.entity.User> checkLoginCredentials(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    public final com.example.partyapp.data.entity.User getUserFromUsername(@org.jetbrains.annotations.NotNull
    java.lang.String username) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object changeUsernameFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newUsername, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object changePfpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newPfp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object updateExpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newExp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.annotation.WorkerThread
    public final java.lang.Object insertNewUser(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/example/partyapp/data/repository/UserRepository$Companion;", "", "()V", "SESSION_INFO", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getDataStore(android.content.Context $this$dataStore) {
            return null;
        }
    }
}