package com.example.partyapp.ui

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel

@Composable
fun EventScreen(
    session: String,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onPfpClicked: ()->Unit,
    onAddEventClicked: ()->Unit
){
    val event = eventViewModel.eventSelected


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(30.dp, 10.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = event!!.image,
            contentDescription = "Image for the event",
            modifier = Modifier
                .weight(0.25f)
                .clip(shape = RoundedCornerShape(10.dp))
        )
        Row(
            modifier = Modifier
                .weight(0.05f)
        ) {
            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
            ) {
                Text(
                    text = event!!.name,
                    style = TextStyle(
                        fontSize = 25.sp,
                        lineHeight = 28.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.End,
                        shadow = Shadow(Color.DarkGray, offset = Offset(0f, 10f), blurRadius = 5f)
                    ),
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
            Row (
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = event.creator.username,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 28.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(400),
                        color = Color(0x80FFFFFF),
                        textAlign = TextAlign.Center,
                        shadow = Shadow(Color.DarkGray, offset = Offset(0f, 10f), blurRadius = 5f)
                    ),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
                )
            }
            Row(
                modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.Bottom)
            ) {
                AsyncImage(
                    model = event.creator.pfp,
                    contentDescription = "Profile picture of event creator",
                    modifier = Modifier
                        .size(35.dp, 35.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                )
            }

        }
        Divider(
            color = Color.White,
            modifier = Modifier.padding(0.dp, 2.dp)
        )
        Column(
            modifier = Modifier.weight(0.15f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)

            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Day of the event",
                    tint = Color.White)
                Text(text = event.day.toString())
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Time of the event",
                    tint = Color.White)
                Text(text = event.starts + "-" + event.ends)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AddLocation,
                    contentDescription = "Location of the event",
                    tint = Color.White)
                Text(text = event.location.city)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Number of participants",
                    tint = Color.White)
                Text(text = event.participants.toString())
            }
        }
        Column(
            modifier = Modifier
                .weight(0.43f)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        ) {
            OutlinedCard(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.hsl(0f, 0f, 1f, 0.10f)),
                border = BorderStroke(1.dp, Color.hsl(0f, 0f, 1f, 0.20f)),
            ){}
        }
    }

}

