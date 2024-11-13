package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.relation.UserCreateEventCrossRef;
import com.example.partyapp.data.repository.UserCreateEventRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/partyapp/viewModel/UserCreateEventViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/partyapp/data/repository/UserCreateEventRepository;", "(Lcom/example/partyapp/data/repository/UserCreateEventRepository;)V", "getCreatorByEventId", "Lkotlinx/coroutines/Job;", "eventId", "", "getEventByCreatorId", "userId", "insertEvent", "userCreateEventCrossRef", "Lcom/example/partyapp/data/relation/UserCreateEventCrossRef;", "app_debug"})
public final class UserCreateEventViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.UserCreateEventRepository repository = null;
    
    @javax.inject.Inject
    public UserCreateEventViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.UserCreateEventRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job insertEvent(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserCreateEventCrossRef userCreateEventCrossRef) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getCreatorByEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getEventByCreatorId(int userId) {
        return null;
    }
}