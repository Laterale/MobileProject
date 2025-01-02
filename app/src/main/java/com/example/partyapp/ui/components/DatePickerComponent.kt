package com.example.partyapp.ui.components

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.partyapp.ui.theme.Glass10
import java.util.Calendar

@Composable
fun PartyDatePickerComponent(
    text: String = "",
    onDatePicked: (Int, Int, Int) -> Unit = {y, m, d -> },
) {
    val context = LocalContext.current
    val tp = DatePickerDialog(
        context,
        { _, y, m, d -> onDatePicked(y, m, d) },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )
    Button(
        onClick = { tp.show() },
        colors = buttonColors(Glass10),
        contentPadding = PaddingValues(0.dp),
//            .border(
//                width = 1.dp,
//                shape = RoundedCornerShape(size = 5.dp),
//                color = Color.hsl(0f, 0f, 1f, 0.20f),
//            )
    ) {
        Text(text = text, color = Color.White)
    }
}