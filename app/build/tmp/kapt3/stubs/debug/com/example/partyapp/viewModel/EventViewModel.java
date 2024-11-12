package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.entity.Event;
import com.example.partyapp.data.repository.EventRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/partyapp/viewModel/EventViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/partyapp/data/repository/EventRepository;", "(Lcom/example/partyapp/data/repository/EventRepository;)V", "allEvents", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/partyapp/data/entity/Event;", "getAllEvents", "()Lkotlinx/coroutines/flow/Flow;", "getEventByEventId", "eventId", "", "getEventsByCity", "city", "", "updateParticipants", "Lkotlinx/coroutines/Job;", "newNum", "app_debug"})
public final class EventViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.EventRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> allEvents = null;
    
    @javax.inject.Inject
    public EventViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.EventRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getAllEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getEventByEventId(int eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.partyapp.data.entity.Event>> getEventsByCity(@org.jetbrains.annotations.NotNull
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job updateParticipants(int newNum, int eventId) {
        return null;
    }
}