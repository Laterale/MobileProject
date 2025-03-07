package com.example.partyapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.partyapp.data.PartyAppDB
import com.example.partyapp.ui.EventScreen
import com.example.partyapp.ui.ExploreScreen
import com.example.partyapp.ui.LoadingScreen
import com.example.partyapp.ui.LoginScreen
import com.example.partyapp.ui.ManageScreen
import com.example.partyapp.ui.MapScreen
import com.example.partyapp.ui.ProfileScreen
import com.example.partyapp.ui.ScanScreen
import com.example.partyapp.ui.SettingsScreen
import com.example.partyapp.ui.theme.UpdateStatusBarColor
import com.example.partyapp.ui.theme.getColorScheme
import com.example.partyapp.ui.userSettings
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.LocationViewModel
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PartyApp : Application(){
    val database by lazy { PartyAppDB.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "notification_channel_id",
            "Notification name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
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
    object Scan : AppScreen("Scan")
}

const val ROOT_ROUTE = "root"
const val DARK_THEME = "Dark"
const val LIGHT_THEME = "Light"

var homeTabIndex = 0
val homeScreens = listOf(AppScreen.Explore.name, AppScreen.Manage.name)
val bottomBarScreens = listOf(AppScreen.Profile.name, AppScreen.Explore.name, AppScreen.Map.name)
val noBottomBarScreens = listOf(AppScreen.Loading.name, AppScreen.Register.name, AppScreen.Login.name, AppScreen.Settings.name)

var settings: SettingsViewModel? = null

@Composable
fun BottomAppBar(
    currentScreen: String,
    navController: NavHostController
){
    var isSelected: Boolean
    var selectedIcon = Icons.Outlined.Clear
    var unselectedIcon = Icons.Filled.Clear
    Column {
        HorizontalDivider(color = Color.White, modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp))
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
        indicator = @Composable { tabPositions ->
            if (homeTabIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[homeTabIndex]),
                    height = 2.dp,
                    color = Color.White,
                )
            }},
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

@Composable
fun NavigationApp(
    session: String,
    navController: NavHostController = rememberNavController()
){
    var colorScheme = getColorScheme()
    var bgGradient by remember { mutableStateOf(value = arrayOf(
        0.1f to colorScheme.primary,
        0.5f to colorScheme.background,
        0.9f to colorScheme.tertiary
    )) }

    settings?.settings?.collectAsState(initial = null)?.also {
        if (it.value != null) {
            userSettings = it.value!!
        }
        colorScheme = getColorScheme(it.value)
        UpdateStatusBarColor(it.value)
        bgGradient = arrayOf(
            0.1f to colorScheme.primary,
            0.5f to colorScheme.background,
            0.9f to colorScheme.tertiary
        )
    }

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: AppScreen.Loading.name
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = bgGradient))
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

    settings = settingsViewModel
    val users = userViewModel.users.collectAsState(initial = listOf())
    if (userViewModel.loggedUser == null && session != "" && session != "default") {
        val current = users.value.find { it.username == session }
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
                settingsViewModel = settingsViewModel,
            )
        }
        composable(route = AppScreen.Profile.name) {
            ProfileScreen(
                onSettingsClicked = { navController.navigate(AppScreen.Settings.name) },
                onQRScanFABClicked = { navController.navigate(AppScreen.Scan.name) },
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                session = session
            )
        }
        composable(route = AppScreen.Map.name) {
            MapScreen(
                eventViewModel = eventViewModel,
                userViewModel = userViewModel,
                onEventMarkerClicked = {
                    navController.navigate(AppScreen.Event.name)
                },
                settingsViewModel = settingsViewModel
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
                userViewModel,
                settingsViewModel
            )
        }
        composable(route = AppScreen.Event.name){
            EventScreen(
                session = session,
                eventViewModel = eventViewModel,
                userViewModel = userViewModel,
                onBackToPrevPage = {
                    navController.navigate(homeScreens[homeTabIndex]) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            )
        }
        composable(route = AppScreen.Scan.name){
            ScanScreen(
                eventViewModel = eventViewModel,
                userViewModel = userViewModel,
                onBackToPrevPage = {
                    navController.navigate(AppScreen.Profile.name) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            )
        }
    }
}