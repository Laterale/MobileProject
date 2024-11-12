package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.relation.UserScansEventCrossRef;
import com.example.partyapp.data.repository.UserScansEventRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/partyapp/viewModel/UserScansEventViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/partyapp/data/repository/UserScansEventRepository;", "(Lcom/example/partyapp/data/repository/UserScansEventRepository;)V", "allScans", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/relation/UserScansEventCrossRef;", "getAllScans", "()Lkotlinx/coroutines/flow/Flow;", "addScan", "Lkotlinx/coroutines/Job;", "userScansEventCrossRef", "getScansFromEventId", "eventId", "", "getScansFromUserId", "userId", "app_debug"})
public final class UserScansEventViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.UserScansEventRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserScansEventCrossRef>> allScans = null;
    
    @javax.inject.Inject
    public UserScansEventViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.UserScansEventRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserScansEventCrossRef>> getAllScans() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getScansFromUserId(int userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getScansFromEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addScan(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserScansEventCrossRef userScansEventCrossRef) {
        return null;
    }
}