package com.example.partyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.partyapp.R
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyDialog(
    title: String,
    content: @Composable() () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            Modifier.clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .background(
                    color = Indigo.copy(alpha = 0.7f)
                        .compositeOver(Color.White.copy(alpha = 0.3f))
                )
        ) {
            Column {
                Row( // header
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title, color = Color.White, style = Typography.bodyMedium)
                    Surface(
                        onClick = onDismissRequest, shape = RectangleShape,
                        modifier = Modifier.padding(2.dp), color = Color.Transparent
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = stringResource(id = R.string.close),
                            tint = Color.White
                        )
                    }
                }
                content.invoke()
            }
        }
    }
}