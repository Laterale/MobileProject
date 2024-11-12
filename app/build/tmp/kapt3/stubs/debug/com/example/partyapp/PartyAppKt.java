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

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u001a\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001eH\u0007\u001a\u001a\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a*\u0010!\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u00012\b\b\u0002\u0010$\u001a\u00020%H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"(\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007\u00f8\u0001\u0000\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0005\"\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\"\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"ROOT_ROUTE", "", "bottomBarScreens", "", "getBottomBarScreens", "()Ljava/util/List;", "colors", "", "Lkotlin/Pair;", "", "Landroidx/compose/ui/graphics/Color;", "getColors", "()[Lkotlin/Pair;", "[Lkotlin/Pair;", "homeScreens", "getHomeScreens", "homeTabIndex", "", "getHomeTabIndex", "()I", "setHomeTabIndex", "(I)V", "noBottomBarScreens", "getNoBottomBarScreens", "BottomAppBar", "", "currentScreen", "navController", "Landroidx/navigation/NavHostController;", "HomeTab", "Landroidx/navigation/NavController;", "NavigationApp", "session", "NavigationGraph", "innerPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "modifier", "Landroidx/compose/ui/Modifier;", "app_debug"})
public final class PartyAppKt {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ROOT_ROUTE = "root";
    private static int homeTabIndex = 0;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> homeScreens = null;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> bottomBarScreens = null;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> noBottomBarScreens = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Pair<java.lang.Float, androidx.compose.ui.graphics.Color>[] colors = null;
    
    public static final int getHomeTabIndex() {
        return 0;
    }
    
    public static final void setHomeTabIndex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> getHomeScreens() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> getBottomBarScreens() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> getNoBottomBarScreens() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final kotlin.Pair<java.lang.Float, androidx.compose.ui.graphics.Color>[] getColors() {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void BottomAppBar(@org.jetbrains.annotations.NotNull
    java.lang.String currentScreen, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void HomeTab(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void NavigationApp(@org.jetbrains.annotations.NotNull
    java.lang.String session, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void NavigationGraph(androidx.navigation.NavHostController navController, androidx.compose.foundation.layout.PaddingValues innerPadding, java.lang.String session, androidx.compose.ui.Modifier modifier) {
    }
}