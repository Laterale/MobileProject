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

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\t\u0007\b\t\n\u000b\f\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\t\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/partyapp/AppScreen;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "Event", "Explore", "Loading", "Login", "Manage", "Map", "Profile", "Register", "Settings", "Lcom/example/partyapp/AppScreen$Event;", "Lcom/example/partyapp/AppScreen$Explore;", "Lcom/example/partyapp/AppScreen$Loading;", "Lcom/example/partyapp/AppScreen$Login;", "Lcom/example/partyapp/AppScreen$Manage;", "Lcom/example/partyapp/AppScreen$Map;", "Lcom/example/partyapp/AppScreen$Profile;", "Lcom/example/partyapp/AppScreen$Register;", "Lcom/example/partyapp/AppScreen$Settings;", "app_debug"})
public abstract class AppScreen {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    
    private AppScreen(java.lang.String name) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Manage;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Manage extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Manage INSTANCE = null;
        
        private Manage() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Explore;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Explore extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Explore INSTANCE = null;
        
        private Explore() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Settings;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Settings extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Settings INSTANCE = null;
        
        private Settings() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Login;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Login extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Login INSTANCE = null;
        
        private Login() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Event;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Event extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Event INSTANCE = null;
        
        private Event() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Map;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Map extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Map INSTANCE = null;
        
        private Map() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Profile;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Profile extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Profile INSTANCE = null;
        
        private Profile() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Register;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Register extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Register INSTANCE = null;
        
        private Register() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/AppScreen$Loading;", "Lcom/example/partyapp/AppScreen;", "()V", "app_debug"})
    public static final class Loading extends com.example.partyapp.AppScreen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.AppScreen.Loading INSTANCE = null;
        
        private Loading() {
            super(null);
        }
    }
}