package com.example.partyapp.data.repository;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.example.partyapp.R;
import kotlinx.coroutines.flow.Flow;
import java.io.IOException;

/**
 * Repository that stores the user's preferences for the ui theme
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/partyapp/data/repository/SettingsRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "preferenceFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getPreferenceFlow", "()Lkotlinx/coroutines/flow/Flow;", "saveToDataStore", "", "theme", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class SettingsRepository {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.partyapp.data.repository.SettingsRepository.Companion Companion = null;
    private static final kotlin.properties.ReadOnlyProperty dataStore$delegate = null;
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> UI_THEME = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.String> preferenceFlow = null;
    
    public SettingsRepository(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getPreferenceFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveToDataStore(@org.jetbrains.annotations.NotNull
    java.lang.String theme, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/example/partyapp/data/repository/SettingsRepository$Companion;", "", "()V", "UI_THEME", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getDataStore(android.content.Context $this$dataStore) {
            return null;
        }
    }
}