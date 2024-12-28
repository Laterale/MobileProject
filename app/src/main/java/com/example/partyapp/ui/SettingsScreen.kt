package com.example.partyapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.partyapp.DARK_THEME
import com.example.partyapp.LIGHT_THEME
import com.example.partyapp.data.UserSettings
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel

var userSettings = UserSettings(
    useSystemTheme = false,
    customTheme = "Light"
)

@Composable
fun SettingsScreen(
    navigateToLogin: () -> Unit,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(30.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        SystemThemeSetting(settingsViewModel)
        ThemeSetting(settingsViewModel)
        LogoutButton(navigateToLogin, userViewModel)
    }
}

@Composable
fun LogoutButton(
    navigateToLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Glass10,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, Glass20),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            userViewModel.clearSession()
            navigateToLogin()
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Logout", style = Typography.bodyMedium)
    }
}

@Composable
fun SystemThemeSetting(
    settingsViewModel: SettingsViewModel
) {
    var checked by remember { mutableStateOf(false) }
    settingsViewModel.settings.collectAsState(initial = userSettings)
        .also {
            if (it.value != null) {
                checked = it.value!!.useSystemTheme
            }
        }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Use system theme", color = Color.White)
        Switch(
            checked = checked, onCheckedChange = {
                checked = it
                userSettings = userSettings.copy(useSystemTheme = checked)
                settingsViewModel.saveSettings(userSettings)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, checkedTrackColor = Color.Cyan,
                uncheckedThumbColor = Color.White, uncheckedTrackColor = Indigo
            ),
            modifier = Modifier
        )
    }
}

@Composable
fun ThemeSetting(
    settingsViewModel: SettingsViewModel
) {
    var checked by remember { mutableStateOf(false) }
    settingsViewModel.settings.collectAsState(initial = userSettings)
        .also {
            if (it.value != null) {
                checked = it.value!!.customTheme == DARK_THEME
            }
        }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Dark mode", color = Color.White)
        Switch(
            checked = checked, onCheckedChange = {
                checked = it
                userSettings = userSettings.copy(customTheme = if (checked) DARK_THEME else LIGHT_THEME)
                settingsViewModel.saveSettings(userSettings)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, checkedTrackColor = Color.Cyan,
                uncheckedThumbColor = Color.White, uncheckedTrackColor = Indigo
            ),
            enabled = !userSettings.useSystemTheme,
            modifier = Modifier
        )
    }
}
