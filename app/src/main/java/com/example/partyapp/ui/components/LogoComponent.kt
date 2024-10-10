package com.example.partyapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyapp.R

@Composable
fun partyAppLogo(color: Color) {
    Icon(
        painter = painterResource(id = R.drawable.logo_icon),
        contentDescription = "Logo",
        tint = color,
        modifier = Modifier.size(80.dp)
    )
    Text(
        text = stringResource(id = R.string.app_name),
        style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
        color = color
    )
}