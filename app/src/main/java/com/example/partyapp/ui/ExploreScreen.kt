package com.example.partyapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.ui.components.EventCard
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.LocationViewModel
import com.example.partyapp.viewModel.UserViewModel


@Composable
fun ExploreScreen(
    onEventClicked: ()->Unit,
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    locationViewModel: LocationViewModel,
    session: String
){
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp, 10.dp, 30.dp, 0.dp)) {
        FiltersBar()
        HorizontalDivider(color = Color.White)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(events) { event ->
                EventCard(
                    onEventClicked,
                    eventViewModel,
                    userViewModel,
                    event,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun FiltersBar(){
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
                contentDescription = stringResource(id = R.string.filters),
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Filled.AreaChart,
                contentDescription = stringResource(id = R.string.filters),
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
