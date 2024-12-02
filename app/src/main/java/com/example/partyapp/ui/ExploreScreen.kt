package com.example.partyapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.LocationViewModel


@Composable
fun ExploreScreen(
    onEventClicked: ()->Unit,
    eventViewModel: EventViewModel,
    locationViewModel: LocationViewModel,
    session: String
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp, 10.dp, 30.dp, 0.dp)) {
        Row(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
                .height(25.dp)
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Filters",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.AreaChart,
                    contentDescription = "Filters",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Row() {
            Divider(
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val events = eventViewModel.events.collectAsState(initial = listOf()).value
            for(event in events){
                Row(
                    modifier = Modifier.padding(0.dp, 20.dp,0.dp, 0.dp).height(80.dp)
                ) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(Color.hsl(0f, 0f, 1f, 0.10f)),
                        border = BorderStroke(1.dp, Color.hsl(0f, 0f, 1f, 0.20f)),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(0.25f)
                            ) {
                                AsyncImage(
                                    model = null,
                                    contentDescription = "Creator pfp",
                                    modifier = Modifier
                                        .padding(10.dp,9.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(0.45f)
                            ) {
                                Text(
                                    text = event.name,
                                    modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 5.dp),
                                    color = Color.White
                                )
                                Text(
                                    text = "eventCreator.username",
                                    color = Color.White,
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
                                )
                            }
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(0.30f)
                            ) {
                                AsyncImage(
                                    model = event.image,
                                    contentDescription = "event image",
                                    modifier = Modifier
                                        .padding(1.dp)
                                        .clip(RoundedCornerShape(0.dp, 11.dp, 11.dp, 0.dp))
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(0.dp, 20.dp,0.dp, 0.dp)
            ){

            }
        }
    }
}

