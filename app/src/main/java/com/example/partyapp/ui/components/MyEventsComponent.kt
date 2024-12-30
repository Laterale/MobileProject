package com.example.partyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import com.example.partyapp.services.EventFactory
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel

@Composable
fun MyEvents(
    currentUser: User,
    eventViewModel: EventViewModel,
    onEventClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
        .filter { it.creator.id == currentUser.id }
    Column(modifier = modifier.fillMaxWidth().padding(top = 20.dp)) {
        Text(
            text = stringResource(id = R.string.your_events),
            style = Typography.titleMedium,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth().height(120.dp).padding(vertical = 20.dp)) {
            item {
                AddEvent(onAddEvent = {
                    val newEvent = EventFactory().createEmptyEvent(creator = currentUser)
                    eventViewModel.selectEvent(newEvent)
                    onEventClicked.invoke()
                })
            }
            items(events) { event ->
                EventThumbnail(event, onEventClicked = {
                    eventViewModel.selectEvent(event)
                    onEventClicked()
                })
            }
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
        colors = IconButtonDefaults.iconButtonColors(Glass10)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.icon_add),
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun EventThumbnail(
    event: Event,
    onEventClicked: () -> Unit
) {
    IconButton(
        onClick = onEventClicked,
        modifier = Modifier
            .padding(5.dp, 0.dp)
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(15.dp)),
        colors = IconButtonDefaults.iconButtonColors(Glass10)
    ) {
        AsyncImage(
            model = event.image,
            contentDescription = stringResource(id = R.string.lbl_event_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
        )
    }
}