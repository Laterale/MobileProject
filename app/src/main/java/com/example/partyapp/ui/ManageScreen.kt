package com.example.partyapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyapp.R
import com.example.partyapp.ui.components.MyEvents
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
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
        Divider(color = Color.White)
        AddedEvents(onEventClicked)
    }
}

@Composable
fun ShowTemplateThumbnails() {
    var i = 0
    while (i != 10) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .width(80.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(15.dp)),
            colors = IconButtonDefaults.iconButtonColors(Glass10)

        ) {}
        i += 1
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTemplateEvents(onEventClicked: () -> Unit) {
    var i = 0
    while(i != 10){
        Row(
            modifier = Modifier.padding(0.dp, 0.dp,0.dp, 20.dp)
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = CardDefaults.cardColors(Glass10),
                border = BorderStroke(1.dp, Glass20),
                onClick = {
                    onEventClicked()
                }  //evento generico, da collegare al viewmodel
            ) {}
            i += 1
        }
    }
}

@Composable
fun YourEvents() {
    Text(
        text = "Your events",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            color = Color.hsl(0f, 0f, 1f, 0.70f),
            shadow = Shadow(Color.DarkGray, offset = Offset(0f, 5f), blurRadius = 10f)),
        modifier = Modifier
            .padding(5.dp, 0.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(0.dp, 20.dp)
            .horizontalScroll(ScrollState(0))
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .width(80.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(15.dp)),
            colors = IconButtonDefaults.iconButtonColors(Glass10)

        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Create new event",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )

        }
        ShowTemplateThumbnails()
    }
}

@Composable
fun AddedEvents(onEventClicked: () -> Unit) {
    Text(
        text = "Added events",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            color = Color.hsl(0f, 0f, 1f, 0.70f),
            shadow = Shadow(Color.DarkGray, offset = Offset(0f, 5f), blurRadius = 10f)),
        modifier = Modifier
            .padding(5.dp, 15.dp, 5.dp, 15.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
    ) {
        ShowTemplateEvents(onEventClicked)
    }
}
