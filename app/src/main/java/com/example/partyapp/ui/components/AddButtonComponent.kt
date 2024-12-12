package com.example.partyapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(
    onAdd: () -> Unit,
    contentDescription: String = "",
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onAdd,
        modifier = modifier.clip(RoundedCornerShape(15.dp)),
        colors = IconButtonDefaults.iconButtonColors(Color.hsl(0f, 0f, 1f, 0.10f))
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}