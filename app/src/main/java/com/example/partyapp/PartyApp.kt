package com.example.partyapp

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.partyapp.data.PartyAppDB
import dagger.hilt.android.HiltAndroidApp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.partyapp.ui.EventScreen
import com.example.partyapp.ui.ExploreScreen
import com.example.partyapp.ui.LoadingScreen
import com.example.partyapp.ui.LoginScreen
import com.example.partyapp.ui.ManageScreen
import com.example.partyapp.ui.MapScreen
import com.example.partyapp.ui.ProfileScreen
import com.example.partyapp.ui.SettingsScreen
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.LocationViewModel
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel


@HiltAndroidApp
class PartyApp : Application(){
    val database by lazy { PartyAppDB.getDatabase(this) }
}

sealed class AppScreen(val name: String){
    object Manage : AppScreen("Manage")
    object Explore : AppScreen("Explore")
    object Settings : AppScreen("Settings")
    object Login : AppScreen("Login")
    object Event : AppScreen("Event")
    object Map : AppScreen("Map")
    object Profile : AppScreen("Profile")
    object Register : AppScreen("Register")
    object Loading : AppScreen("Loading")
}

const val ROOT_ROUTE = "root"
var homeTabIndex = 0
val homeScreens = listOf(AppScreen.Explore.name, AppScreen.Manage.name)
val bottomBarScreens = listOf(AppScreen.Profile.name, AppScreen.Explore.name, AppScreen.Map.name)
val noBottomBarScreens = listOf(AppScreen.Loading.name, AppScreen.Register.name, AppScreen.Login.name, AppScreen.Settings.name)
val colors = arrayOf(
    0.1f to Color(0xffDD9191),
    0.5f to Color(0xff46389D),
    0.9f to Color(0xff090F48))

@Composable
fun BottomAppBar(
    currentScreen: String,
    navController: NavHostController
){
    var isSelected: Boolean
    var selectedIcon = Icons.Outlined.Clear
    var unselectedIcon = Icons.Filled.Clear
    Column {
        Row(
            modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)
        ) {
            Divider(
                color = Color.White
            )
        }
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ) {
            bottomBarScreens.forEach { item->
                when(item){
                    AppScreen.Profile.name -> {
                        unselectedIcon = Icons.Outlined.Person
                        selectedIcon = Icons.Filled.Person
                    }
                    AppScreen.Explore.name -> {
                        unselectedIcon = Icons.Outlined.Home
                        selectedIcon = Icons.Filled.Home
                    }
                    AppScreen.Map.name -> {
                        unselectedIcon = Icons.Outlined.Place
                        selectedIcon = Icons.Filled.Place
                    }
                }
                isSelected = currentScreen == item || item == "Explore" && currentScreen in homeScreens
                NavigationBarItem(
                    onClick = {
                        if(item == "Explore") navController.navigate(if (homeTabIndex == 0) item else AppScreen.Manage.name) { navController.graph.startDestinationRoute?.let { popUpTo(it){ inclusive = true } } }
                        else navController.navigate(item) { navController.graph.startDestinationRoute?.let { popUpTo(it){ inclusive = false } } }
                              },
                    icon = {
                        if(isSelected) Icon(
                            imageVector = selectedIcon,
                            contentDescription = "Go to $item",
                            tint = Color.Black
                        )
                        else Icon(
                            imageVector = unselectedIcon,
                            contentDescription = "Go to $item",
                            tint = Color.White
                        )
                    },
                    selected = isSelected,
                )
            }
        }
    }
}

@Composable
fun HomeTab(
    navController: NavController
){
    TabRow(
        selectedTabIndex = homeTabIndex,
        containerColor = Color.Transparent,
        divider = {}
    ) {
        homeScreens.forEachIndexed { index, item ->
            Tab(
                selected = index == homeTabIndex,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                onClick = {
                    homeTabIndex = index
                    navController.navigate(item){
                        navController.popBackStack()
                    }
                          },
                text = {
                    Text(
                        text = item,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            color = Color(0xFFFFFFFF),
                            shadow =
                                if (index == homeTabIndex) Shadow(Color.DarkGray, offset = Offset(0f, 5f), blurRadius = 8f)
                                else Shadow(Color.Transparent, offset = Offset(0f, 5f), blurRadius = 8f)
                        )
                    ) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(
    session: String,
    navController: NavHostController = rememberNavController()
){

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: AppScreen.Loading.name
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = colors))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {if (currentScreen in homeScreens) HomeTab(navController = navController)},
            bottomBar = {if(currentScreen !in noBottomBarScreens)BottomAppBar(navController = navController, currentScreen = currentScreen)}
        ) { innerPadding ->
            NavigationGraph(navController, innerPadding, session= session)
        }
    }
}

@Composable
private fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    session: String,
    modifier: Modifier = Modifier
) {

    val settingsViewModel = hiltViewModel<SettingsViewModel>()
    val userViewModel = hiltViewModel<UserViewModel>()
    val locationViewModel = hiltViewModel<LocationViewModel>()
    val eventViewModel = hiltViewModel<EventViewModel>()

    val users = userViewModel.users.collectAsState(initial = listOf())
    if (userViewModel.loggedUser == null && session != "" && session != "default") {
        var current = users.value.find { it.username == session }
        current?.let { userViewModel.selectUser(it) }
    }

    // TODO: review start destination logic
    NavHost(
        navController = navController,
        startDestination =  if(session == "default") AppScreen.Loading.name
            else if(session == "") AppScreen.Login.name
            else (if (homeTabIndex == 0) AppScreen.Explore.name else AppScreen.Manage.name),
        // startDestination = if(homeTabIndex == 0) AppScreen.Explore.name else AppScreen.Manage.name,
        route = ROOT_ROUTE,
        modifier = modifier.padding(innerPadding)
    ) {
        composable(route = AppScreen.Manage.name) {
            ManageScreen(
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                onEventClicked = {
                    navController.navigate(AppScreen.Event.name)
                },
            )
        }
        composable(route = AppScreen.Explore.name) {
            ExploreScreen(
                onEventClicked = {
                    navController.navigate(AppScreen.Event.name)
                },
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                locationViewModel = locationViewModel,
                session = session
            )
        }
        composable(route = AppScreen.Profile.name) {
            ProfileScreen(
                onEventClicked = {
                    navController.navigate(AppScreen.Event.name)
                },
                onSettingsClicked = {
                    navController.navigate(AppScreen.Settings.name)
                },
                userViewModel = userViewModel,
                settingsViewModel = settingsViewModel,
                session = session
            )
        }
        composable(route = AppScreen.Map.name) {
            MapScreen(

            )
        }
        composable(route = AppScreen.Loading.name){
            LoadingScreen(
                navigateToLogin = {
                    navController.navigate(AppScreen.Login.name){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                navigateToHome = {
                    navController.navigate(if(homeTabIndex == 0) AppScreen.Explore.name else AppScreen.Manage.name){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                session
            )
        }
        composable(route = AppScreen.Login.name){
            LoginScreen(
                onSuccessfulLogin = {
                    navController.navigate(AppScreen.Explore.name) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                onRegisterButtonClicked = { navController.navigate(AppScreen.Register.name) },
                userViewModel = userViewModel
            )
        }
        composable(route = AppScreen.Settings.name){
            SettingsScreen(
                navigateToLogin = {
                    navController.navigate(AppScreen.Login.name) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                userViewModel
            )
        }
        composable(route = AppScreen.Event.name){
            EventScreen(
                session = session,
                eventViewModel = eventViewModel,
                userViewModel = userViewModel,
                onSaveEvent = {},
                onAddEventClicked = {},
            )
        }
    }
}