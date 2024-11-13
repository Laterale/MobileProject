package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.relation.UserAddEventCrossRef;
import com.example.partyapp.data.repository.UserAddEventRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/partyapp/viewModel/UserAddEventViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/partyapp/data/repository/UserAddEventRepository;", "(Lcom/example/partyapp/data/repository/UserAddEventRepository;)V", "allParticipants", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/relation/UserAddEventCrossRef;", "getAllParticipants", "()Lkotlinx/coroutines/flow/Flow;", "addParticipant", "Lkotlinx/coroutines/Job;", "userAddEventCrossRef", "deleteParticipant", "getParticipantsFromEventId", "eventId", "", "app_debug"})
public final class UserAddEventViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.UserAddEventRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> allParticipants = null;
    
    @javax.inject.Inject
    public UserAddEventViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.UserAddEventRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.relation.UserAddEventCrossRef>> getAllParticipants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job getParticipantsFromEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef userAddEventCrossRef) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job deleteParticipant(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.relation.UserAddEventCrossRef userAddEventCrossRef) {
        return null;
    }
}