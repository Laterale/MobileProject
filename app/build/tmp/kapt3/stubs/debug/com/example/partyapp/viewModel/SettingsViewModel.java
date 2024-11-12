package com.example.partyapp.viewModel;

import androidx.lifecycle.ViewModel;
import com.example.partyapp.data.repository.SettingsRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/example/partyapp/viewModel/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/example/partyapp/data/repository/SettingsRepository;", "(Lcom/example/partyapp/data/repository/SettingsRepository;)V", "theme", "Lkotlinx/coroutines/flow/Flow;", "", "getTheme", "()Lkotlinx/coroutines/flow/Flow;", "saveTheme", "", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.partyapp.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.String> theme = null;
    
    @javax.inject.Inject
    public SettingsViewModel(@org.jetbrains.annotations.NotNull
    com.example.partyapp.data.repository.SettingsRepository settingsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getTheme() {
        return null;
    }
    
    public final void saveTheme(@org.jetbrains.annotations.NotNull
    java.lang.String theme) {
    }
}