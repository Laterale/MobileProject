package com.example.partyapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.example.partyapp.DARK_THEME
import com.example.partyapp.data.UserSettings

private val DarkColorScheme = darkColorScheme(
    primary = DarkTeal,
    secondary = Indigo,
    tertiary = Color.Black,
    background = Indigo,
    surface = DarkTeal,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Salmon,
    secondary = Violet,
    tertiary = Indigo,
    background = Violet,
    surface = Salmon,
    onSurface = Violet

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun getDefaultButtonColors(): ButtonColors {
    return buttonColors(
        backgroundColor = Color.White.copy(alpha = 0.1f),
        contentColor = Color.White,
        disabledBackgroundColor = Color.White.copy(alpha = 0.05f),
        disabledContentColor = Color.Gray
    )
}

@Composable
fun getColorScheme(
    settings: UserSettings? = null,
): ColorScheme {
    return if (settings == null) {
        LightColorScheme
    } else if (settings.useSystemTheme) {
        if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    } else if (settings.customTheme == DARK_THEME) DarkColorScheme else LightColorScheme
}

@Composable
fun PartyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme()
    UpdateStatusBarColor()
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun UpdateStatusBarColor(settings: UserSettings? = null,) {
    val view = LocalView.current
    val colorScheme = getColorScheme(settings)
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}