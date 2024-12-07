package com.example.partyapp.ui

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.data.entity.Event
import com.example.partyapp.services.EventFactory
import com.example.partyapp.ui.components.AddButton
import com.example.partyapp.ui.components.PartyTextField
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel
import java.util.Calendar

val factory = EventFactory()
var event: Event = factory.CreateEmpty()

@Composable
fun EventScreen(
    session: String,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onPfpClicked: () -> Unit,
    onAddEventClicked: () -> Unit
) {
    event = eventViewModel.eventSelected ?: factory.createEmptyEvent(userViewModel.loggedUser!!)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(30.dp, 10.dp, 30.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        EventImage(modifier = Modifier.fillMaxHeight(0.25f))
        EventTitle()
        EventAuthor()
        Divider(color = Color.White, modifier = Modifier.padding(vertical = 2.dp))
        EventDetails(modifier = Modifier.fillMaxWidth())
        EventDescription()
    }
}

@Composable
fun EventImage(modifier: Modifier = Modifier) {
    if (event.eventId == -1) {
        AddButton(
            onAdd = { /*TODO*/ },
            modifier = modifier.fillMaxWidth()
        )
    } else {
        AsyncImage(
            model = event.image,
            contentDescription = "Event image",
            modifier = modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.Black)
        )
    }
}

@Composable
fun EventDetails(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Day of the event",
                    tint = Color.White
                )
                Text(text = event.day.toString(), color = Color.White)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(
                    imageVector = Icons.Filled.AddLocation,
                    contentDescription = "Location of the event",
                    tint = Color.White
                )
                Text(text = event.location.city, color = Color.White)
            }
        }
        item {
            EventTimeDetail()
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Number of participants",
                    tint = Color.White
                )
                Text(text = event.participants.toString(), color = Color.White)
            }
        }
    }
}


@Composable
fun EventTitle(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        if (event.eventId == -1) {
            var title: String by remember { mutableStateOf("") }
            PartyTextField(
                value = title, onValueChange = { title = it },
                placeholder = "Party Name",
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = event.name,
                style = TextStyle(
                    fontSize = 25.sp,
                    lineHeight = 28.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    shadow = Shadow(Color.DarkGray, offset = Offset(0f, 10f), blurRadius = 5f)
                ),
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Composable
fun EventAuthor(modifier: Modifier = Modifier) {
    val pfpSize = 25.dp
    Row(modifier = modifier) {
        AsyncImage(
            model = event.creator.pfp,
            contentDescription = "Profile picture of event creator",
            modifier = Modifier
                .size(pfpSize)
                .clip(CircleShape)
                .background(Color.Black)
        )
        Text(
            text = event.creator.username,
            style = Typography.labelSmall,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 5.dp)
        )
    }
}

@Composable
fun EventDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(bottom = 10.dp)
    ) {
        OutlinedCard(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(Color.hsl(0f, 0f, 1f, 0.10f)),
            border = BorderStroke(1.dp, Color.hsl(0f, 0f, 1f, 0.20f)),
        ) {}
    }
}

@Composable
fun EventTimeDetail() {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = "Time of the event",
            tint = Color.White
        )
        if (event.eventId == -1) {
            val context = LocalContext.current
            val tp = TimePickerDialog(
                context,
                { _, i, i2 ->
                    Log.d("TIME_PICKER", "$i:$i2")
                    event.starts = "$i:$i2"
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
            )
            Button(
                colors = buttonColors(Color.hsl(0f, 0f, 1f, 0.10f)),
                onClick = { tp.show() }
            ) {
                Text(text = event.starts, color = Color.White)
            }
            Text(text = "-")
            Button(
                colors = buttonColors(Color.hsl(0f, 0f, 1f, 0.10f)),
                onClick = { tp.show() }
            ) {
                Text(text = event.ends, color = Color.White)
            }
        } else {
            Text(
                text = event.starts + "-" + event.ends,
                color = Color.White
            )
        }
    }
}
