package com.example.partyapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.getDefaultButtonColors

@Composable
fun AddButton(
    onAdd: () -> Unit,
    contentDescription: String = "",
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onAdd,
        modifier = modifier.clip(RoundedCornerShape(15.dp)),
        colors = IconButtonDefaults.iconButtonColors(Glass10)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 15.dp,
    padding: PaddingValues = PaddingValues(10.dp),
    textColor: Color = Color.White,
    fontSize: TextUnit? = TextUnit.Unspecified,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        colors = getDefaultButtonColors(),
        contentPadding = padding,
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color.White.copy(alpha = 0.2f), Color.Transparent)
            )
        ),
        modifier = modifier,
        elevation = elevation(0.dp)
    ) {
        Text(
            text = text, color = textColor, fontSize = fontSize ?: TextUnit.Unspecified,
        )
    }
}

@Composable
fun PartyIconButton(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    cornerRadius: Dp = 15.dp,
    textColor: Color = Color.White,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        colors = getDefaultButtonColors(),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color.White.copy(alpha = 0.2f), Color.Transparent)
            )
        ),
        elevation = elevation(0.dp),
        modifier = modifier,
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = textColor)
        if (text != "") {
            Text(text = text, color = textColor, modifier = Modifier.padding(start = 10.dp))
        }
    }
}
