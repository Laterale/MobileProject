package com.example.partyapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
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
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel

@Composable
fun EventCard(
    onEventClicked: () -> Unit,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    event: Event,
    modifier: Modifier = Modifier
) {
    val user = userViewModel.users.collectAsState(initial = listOf())
        .value.find { it.username == event.creator.username }
    Row(modifier = modifier.height(80.dp)) {
        OutlinedCard(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(Glass10),
            border = BorderStroke(1.dp, Glass20),
            onClick = {
                eventViewModel.selectEvent(event)
                onEventClicked()
            },
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(Modifier.fillMaxHeight().fillMaxWidth(0.25f)) {
                    AsyncImage(
                        model = user?.pfp ?: event.creator.pfp,
                        contentDescription = stringResource(id = R.string.lbl_creator_pfp),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(10.dp, 9.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                    )
                }
                Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                    Text(
                        text = event.name,
                        modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 5.dp),
                        color = Color.White
                    )
                    Text(
                        text = event.creator.username,
                        color = Color.White,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
                    )
                }
                Column(modifier = Modifier.fillMaxHeight()) {
                    AsyncImage(
                        model = event.image,
                        contentDescription = stringResource(id = R.string.lbl_event_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(1.dp)
                            .clip(RoundedCornerShape(0.dp, 11.dp, 11.dp, 0.dp))
                    )
                }
            }
        }
    }
}