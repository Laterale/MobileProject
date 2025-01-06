package com.example.partyapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.partyapp.ui.components.EventCard
import com.example.partyapp.ui.components.IconDatePickerComponent
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.viewModel.SettingsViewModel
import java.time.ZoneId
import java.util.Calendar
import kotlin.math.roundToInt


@Composable
fun ExploreScreen(
    onEventClicked: ()->Unit,
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    settingsViewModel: SettingsViewModel,
){
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp, 10.dp, 30.dp, 0.dp)) {
        FiltersBar(settingsViewModel)
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

private fun dateToStr(date: Calendar): String {
    return date.time.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .toString()
}

@Composable
fun FiltersBar(
    settingsViewModel: SettingsViewModel
){
    val cal = Calendar.getInstance()
    var date: String by remember {
        mutableStateOf(userSettings.dateFilter)
    }
    var sliderPosition by remember { mutableFloatStateOf(userSettings.rangeFilter.toFloat()) }
    Row(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .height(25.dp)
    ) {
        Row(modifier = Modifier.weight(0.4f)) {
            IconDatePickerComponent(
                date,
                onDatePicked = { year, month, day ->
                    cal.apply { set(year, month, day) }
                    userSettings = userSettings.copy(dateFilter = dateToStr(cal))
                    settingsViewModel.saveSettings(userSettings)
                    date = dateToStr(cal)
                }
            )
        }
        Row(modifier = Modifier.weight(0.45f)) {
            androidx.compose.material.Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it.roundToInt().toFloat() },
                onValueChangeFinished = {
                    userSettings = userSettings.copy(rangeFilter = sliderPosition.roundToInt())
                    settingsViewModel.saveSettings(userSettings)
                },
                valueRange = 5f..50f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Glass20,
                )
            )
        }
        Row(
            modifier = Modifier.weight(0.15f),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = sliderPosition.roundToInt().toString() + "km")
        }
    }
}
