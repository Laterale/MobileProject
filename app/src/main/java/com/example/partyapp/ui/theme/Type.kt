package com.example.partyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.partyapp.R

// Set of Material typography styles to start with
val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
    labelSmall = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
        color = Color(0x80FFFFFF),
        textAlign = TextAlign.Center,
        shadow = Shadow(Color.DarkGray, offset = Offset(0f, 10f), blurRadius = 5f)
    ),
    labelMedium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        shadow = Shadow(Color.DarkGray, offset = Offset(0f, 10f), blurRadius = 5f)
    ),
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = Color.hsl(0f, 0f, 1f, 0.70f),
        shadow = Shadow(Color.DarkGray, offset = Offset(0f, 5f), blurRadius = 10f)
    )
)