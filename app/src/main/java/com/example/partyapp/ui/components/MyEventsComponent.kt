package com.example.partyapp.ui.components

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.partyapp.data.entity.User
import com.example.partyapp.services.EventFactory
import com.example.partyapp.ui.EventCard
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel

@Composable
fun MyEvents(
    currentUser: User,
    eventViewModel: EventViewModel,
    onEventClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Your events",
            style = Typography.titleMedium,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 20.dp)
                .horizontalScroll(ScrollState(0))
        ) {
            AddEvent(onAddEvent = {
                val newEvent = EventFactory().createEmptyEvent(creator = currentUser)
                eventViewModel.selectEvent(newEvent)
                onEventClicked.invoke()
            })
            ShowTemplateThumbnails(
                currentUser = currentUser,
                eventViewModel = eventViewModel,
                onEventClicked = onEventClicked
            )
        }
    }
}

@Composable
fun AddEvent(
    onAddEvent: () -> Unit
) {
    IconButton(
        onClick = onAddEvent,
        modifier = Modifier
            .padding(5.dp, 0.dp)
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(15.dp)),
        colors = IconButtonDefaults.iconButtonColors(Color.hsl(0f, 0f, 1f, 0.10f))
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Create new event",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun ShowTemplateThumbnails(
    currentUser: User,
    eventViewModel: EventViewModel,
    onEventClicked: () -> Unit
) {
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
        .filter { it.creator.id == currentUser.id }

    for(event in events) {
        IconButton(
            onClick = onEventClicked,
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .width(80.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(15.dp)),
            colors = IconButtonDefaults.iconButtonColors(Color.hsl(0f, 0f, 1f, 0.10f))
        ) {
            AsyncImage(
                model = event.image,
                contentDescription = "event image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black),
            )
        }
    }
}