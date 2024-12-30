package com.example.partyapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.ui.components.EventCard
import com.example.partyapp.ui.components.MyEvents
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel


@Composable
fun ManageScreen(
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    onEventClicked: () -> Unit,
){
    var currentUser = userViewModel.loggedUser
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 20.dp, 30.dp, 0.dp)
    ) {
        if (currentUser != null) {
            MyEvents(currentUser, eventViewModel, onEventClicked)
        }
        HorizontalDivider(color = Color.White)
        AddedEvents(onEventClicked, eventViewModel, userViewModel)
    }
}

@Composable
fun AddedEventsCards(
    onEventClicked: () -> Unit,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel
) {
    val added = eventViewModel.allParticipants.collectAsState(initial = listOf()).value
        .filter { it.id == userViewModel.loggedUser!!.id }
        .map { it.eventId }
        .distinct()
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
        .filter { added.contains(it.eventId) && it.creator.id != userViewModel.loggedUser!!.id }
    for (event in events) {
        EventCard(onEventClicked, eventViewModel, userViewModel, event)
    }
    if (events.isEmpty()) {
        Text(
            text = stringResource(id = R.string.lbl_no_added_events),
            style = Typography.titleMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(5.dp, 15.dp, 5.dp, 15.dp),
        )
    }
}

@Composable
fun AddedEvents(
    onEventClicked: () -> Unit,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel
) {
    Text(
        text = stringResource(id = R.string.added_events),
        style = Typography.titleMedium,
        modifier = Modifier.padding(5.dp, 15.dp, 5.dp, 15.dp)
    )
    Column(modifier = Modifier.fillMaxSize().verticalScroll(ScrollState(0))) {
        AddedEventsCards(onEventClicked, eventViewModel, userViewModel)
    }
}
