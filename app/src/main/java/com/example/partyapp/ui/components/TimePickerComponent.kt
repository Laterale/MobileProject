package com.example.partyapp.ui.components

import android.app.TimePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Preview
@Composable
fun PartyTimePickerComponent(
    text: String = "test",
    onTimePicked: (Int, Int) -> Unit = {h, m -> },
    is24HourView: Boolean = true,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val tp = TimePickerDialog(
        context,
        { _, i, i2 -> onTimePicked(i, i2) },
        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        Calendar.getInstance().get(Calendar.MINUTE),
        is24HourView
    )
    Button(
        onClick = { tp.show() },
        colors = buttonColors(Color.hsl(0f, 0f, 1f, 0.10f)),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
//            .border(
//                width = 1.dp,
//                shape = RoundedCornerShape(size = 5.dp),
//                color = Color.hsl(0f, 0f, 1f, 0.20f),
//            )
    ) {
        Text(text = text.toString(), color = Color.White)
    }
}