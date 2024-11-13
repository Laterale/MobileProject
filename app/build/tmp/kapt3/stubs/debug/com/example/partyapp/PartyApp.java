package com.example.partyapp;

import android.app.Application;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.TextStyle;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import com.example.partyapp.data.PartyAppDB;
import dagger.hilt.android.HiltAndroidApp;
import com.example.partyapp.viewModel.EventViewModel;
import com.example.partyapp.viewModel.LocationViewModel;
import com.example.partyapp.viewModel.SettingsViewModel;
import com.example.partyapp.viewModel.UserAddEventViewModel;
import com.example.partyapp.viewModel.UserCreateEventViewModel;
import com.example.partyapp.viewModel.UserScansEventViewModel;
import com.example.partyapp.viewModel.UserViewModel;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/example/partyapp/PartyApp;", "Landroid/app/Application;", "()V", "database", "Lcom/example/partyapp/data/PartyAppDB;", "getDatabase", "()Lcom/example/partyapp/data/PartyAppDB;", "database$delegate", "Lkotlin/Lazy;", "app_debug"})
@dagger.hilt.android.HiltAndroidApp
public final class PartyApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy database$delegate = null;
    
    public PartyApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.partyapp.data.PartyAppDB getDatabase() {
        return null;
    }
}