package com.example.partyapp.ui.components

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun PartyTimePickerComponent(
    modifier: Modifier = Modifier,
    text: String = "",
    onTimePicked: (Int, Int) -> Unit = {h, m -> },
    is24HourView: Boolean = true,
) {
    val context = LocalContext.current
    val tp = TimePickerDialog(
        context,
        { _, i, i2 -> onTimePicked(i, i2) },
        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        Calendar.getInstance().get(Calendar.MINUTE),
        is24HourView
    )
    TextButton(
        text = text, onClick = { tp.show() },
//        padding = PaddingValues(0.dp),
        modifier = modifier
    )
}