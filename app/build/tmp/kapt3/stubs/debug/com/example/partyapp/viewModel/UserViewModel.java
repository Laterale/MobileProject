package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.entity.User;
import com.example.partyapp.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fJ\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\fJ\u0016\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0006J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0006J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0006J\u0016\u0010#\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00068F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00100\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006%"}, d2 = {"Lcom/example/partyapp/viewModel/UserViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/partyapp/data/repository/UserRepository;", "(Lcom/example/partyapp/data/repository/UserRepository;)V", "_loggedUser", "Lcom/example/partyapp/data/entity/User;", "loggedUser", "getLoggedUser", "()Lcom/example/partyapp/data/entity/User;", "session", "Lkotlinx/coroutines/flow/Flow;", "", "getSession", "()Lkotlinx/coroutines/flow/Flow;", "users", "", "getUsers", "changePfpFromId", "Lkotlinx/coroutines/Job;", "userId", "", "newPfp", "changeUsernameFromId", "newUsername", "checkLoginCredentials", "username", "password", "clearSession", "", "getUserFromUsername", "insertNewUser", "user", "selectUser", "startSession", "updateExpFromId", "newExp", "app_debug"})
public final class UserViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.UserRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.String> session = null;
    private com.example.partyapp.data.entity.User _loggedUser;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.User>> users = null;
    
    @javax.inject.Inject
    public UserViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.UserRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getSession() {
        return null;
    }
    
    public final void startSession(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User user) {
    }
    
    public final void clearSession() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.partyapp.data.entity.User getLoggedUser() {
        return null;
    }
    
    public final void selectUser(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User user) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.User>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getUserFromUsername(@org.jetbrains.annotations.NotNull
    java.lang.String username) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job checkLoginCredentials(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job changeUsernameFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newUsername) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job changePfpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newPfp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job updateExpFromId(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String newExp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job insertNewUser(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.entity.User user) {
        return null;
    }
}