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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel

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
        ThemeSettings(settingsViewModel)
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
        Text(text = stringResource(R.string.logout), style = Typography.bodyMedium)
    }
}

@Composable
fun ThemeSettings(
    settingsViewModel: SettingsViewModel
) {
    Text(text = stringResource(R.string.theme_settings), style = Typography.titleMedium)
    SysDefaultSetting(settingsViewModel = settingsViewModel)
    DarkThemeSetting(settingsViewModel = settingsViewModel)
}

@Composable
fun DarkThemeSetting(settingsViewModel: SettingsViewModel) {
    val context = LocalContext.current
    var checked by remember { mutableStateOf(false) }
    settingsViewModel.theme.collectAsState(initial = stringResource(R.string.light_theme))
        .also { checked = (it.value == stringResource(R.string.dark_theme)) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.dark_theme),
            color = Color.White
        )
        Switch(
            checked = checked, onCheckedChange = {
                checked = it
                settingsViewModel.saveTheme(
                    if (checked) context.getString(R.string.dark_theme)
                    else context.getString(R.string.light_theme)
                )
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
fun SysDefaultSetting(settingsViewModel: SettingsViewModel) {
    var checked by remember { mutableStateOf(false) }
    settingsViewModel.useSysDefault.collectAsState(initial = "false")
        .also { checked = (it.value == "true") }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.use_system_theme), color = Color.White)
        Switch(
            checked = checked, onCheckedChange = {
                checked = it
                settingsViewModel.saveUseSysDefaultTheme(if (checked) "true" else "false" )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, checkedTrackColor = Color.Cyan,
                uncheckedThumbColor = Color.White, uncheckedTrackColor = Indigo
            ),
            modifier = Modifier
        )
    }
}
