package com.example.partyapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.partyapp.ui.theme.Typography

@Composable
fun StatisticComponent(
    stat : String,
    value : String
){
    Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)) {
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stat,
                style = Typography.labelMedium,
            )
        }
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = value,
                style = Typography.labelMedium,
            )
        }
    }
}