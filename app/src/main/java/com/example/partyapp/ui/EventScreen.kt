package com.example.partyapp.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserAddEventCrossRef
import com.example.partyapp.services.EventFactory
import com.example.partyapp.services.ImageChooserService
import com.example.partyapp.services.NotificationScheduler
import com.example.partyapp.ui.components.AddButton
import com.example.partyapp.ui.components.PartyDatePickerComponent
import com.example.partyapp.ui.components.PartyTextField
import com.example.partyapp.ui.components.PartyTimePickerComponent
import com.example.partyapp.ui.components.TextButton
import com.example.partyapp.ui.theme.GetDefaultButtonColors
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel
import java.io.File
import java.util.Calendar
import java.util.Date

private val factory = EventFactory()
private var event: Event = factory.createEmpty()
private lateinit var loggedUser: User
private var isEditing: Boolean = false

@Composable
fun EventScreen(
    session: String,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onBackToPrevPage: () -> Unit = {}
) {
    event = eventViewModel.eventSelected ?: factory.createEmptyEvent(userViewModel.loggedUser!!)
    loggedUser = userViewModel.loggedUser!!

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
        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp), color = Color.White)
        EventDetails(modifier = Modifier.fillMaxWidth())
        EventDescription(modifier = Modifier.fillMaxHeight(0.8f))
        Actions(
            eventViewModel = eventViewModel,
            onBackToPrevPage = onBackToPrevPage
        )
    }
}

@Composable
private fun EventImage(modifier: Modifier = Modifier) {
    var photoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }
    val setImg: (Uri, String) -> Unit = { uri, path ->
        photoUri = uri
        event = event.copy(image = path)
    }

    if (isEditingMode() && photoUri == Uri.EMPTY) {
        AddEventImageBtn(
            onImageChosen = setImg,
            modifier = modifier.fillMaxWidth()
        )
    } else {
        AsyncImage(
            model = event.image,
            contentDescription = "Event image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.Black)
        )
    }
}

@Composable
private fun AddEventImageBtn(
    onImageChosen: (Uri, String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val imgChooser = ImageChooserService()
    var showDialog: Boolean by remember { mutableStateOf(false) }
    var tempPhotoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            val savedPath = imgChooser.saveImageToInternalStorage(context, uri)
            if (savedPath != null) {
                onImageChosen(uri, savedPath)
            }
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            val file = File(tempPhotoUri.path ?: return@rememberLauncherForActivityResult)
            imgChooser.addPhotoToGallery(context, file) // Notify gallery
            onImageChosen(tempPhotoUri, tempPhotoUri.toString())
        }
    }
    val cameraPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            tempPhotoUri = imgChooser.getTempImageUri(context)
            cameraLauncher.launch(tempPhotoUri)
        }
        else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    AddButton(
        onAdd = { showDialog = true },
        modifier = modifier
    )
    if (showDialog) {
        imgChooser.ChooseImage(
            imagePickerLauncher = imagePickerLauncher,
            cameraPermissionRequest = cameraPermissionRequest,
            onDismissRequest = { showDialog = false }
        )
    }
}

@Preview
@Composable
private fun EventDetails(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        item { EventDateDetail() }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AddLocation,
                    contentDescription = "Location of the event",
                    tint = Color.White
                )
                Text(text = event.location.city, color = Color.White)
            }
        }
        item { EventTimeDetail() }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = if (isEditingMode()) 0.dp else 5.dp, bottom = if (isEditingMode()) 15.dp else 0.dp)
            ) {
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
private fun EventTitle(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        if (isEditingMode()) {
            var title: String by remember { mutableStateOf(event.name) }
            PartyTextField(
                value = title,
                onValueChange = {
                    title = it
                    event = event.copy(name = it)
                },
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
private fun EventAuthor(modifier: Modifier = Modifier) {
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
private fun EventDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        if (isEditingMode()) {
            var des: String by remember { mutableStateOf(event.description) }
            PartyTextField(
                value = des,
                onValueChange = {
                    des = it
                    event = event.copy(description = it)
                },
                placeholder = "Description",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            OutlinedCard(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.hsl(0f, 0f, 1f, 0.10f)),
                border = BorderStroke(1.dp, Color.hsl(0f, 0f, 1f, 0.20f)),
            ) {
                Text(
                    text = event.description,
                    style = Typography.bodyMedium,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(10.dp)
                )
            }
        }
    }
}

private fun dateToStr(date: Date): String {
    return date.toGMTString()
        .split(" ")
        .take(3)
        .reduce {acc, s -> "$acc $s" }
}

@Preview
@Composable
private fun EventDateDetail(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = "Day of the event",
            tint = Color.White
        )
        if (isEditingMode()) {
            var date: String by remember { mutableStateOf(dateToStr(Date())) }
            PartyDatePickerComponent(
                text = date,
                onDatePicked = { year, month, day ->
                    val calendar = Calendar.getInstance()
                        .apply { set(year, month, day) }
                    date = dateToStr(calendar.time)
                    event = event.copy(day = year * 10000 + month * 100 + day)
                }
            )
        } else {
            val calendar = getEventDate()
            Text(
                text = dateToStr(calendar.time),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun EventTimeDetail(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = "Time of the event",
            tint = Color.White
        )
        if (isEditingMode()) {
            var starts: String by remember { mutableStateOf(event.starts) }
            var ends: String by remember { mutableStateOf(event.ends) }
            PartyTimePickerComponent(
                text = starts,
                onTimePicked = { h, m ->
                    starts = "%02d:%02d".format(h, m)
                    event = event.copy(starts = starts)
                }
            )
            Text(text = "-", color = Color.White)
            PartyTimePickerComponent(
                text = ends,
                onTimePicked = { h, m ->
                    ends = "%02d:%02d".format(h, m)
                    event = event.copy(ends = ends)
                }
            )
        } else {
            Text(
                text = event.starts + "-" + event.ends,
                color = Color.White
            )
        }
    }
}

@Composable
private fun Actions(
    eventViewModel: EventViewModel,
    onBackToPrevPage: () -> Unit = {}
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (!isCreatedByCurrentUser()) {
            AddEventButton(eventViewModel)
        } else if (isNewEvent()) {
            SaveDiscardBtns(eventViewModel, onBackToPrevPage)
        } else {
            DeleteEventButton(eventViewModel, onBackToPrevPage)
        }
    }
}

@Composable
private fun SaveDiscardBtns(
    eventViewModel: EventViewModel,
    onBackToPrevPage: () -> Unit = {}
) {
    val context = LocalContext.current
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
    TextButton(
        text = "Discard",
        onClick = onBackToPrevPage,
        modifier = Modifier.fillMaxWidth(0.5f),
    )
    TextButton(
        text = "Save",
        onClick = {
            saveNewEvent(context, eventViewModel, events)
            onBackToPrevPage()
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AddEventButton(
    eventViewModel: EventViewModel,
    //onBackToPrevPage: () -> Unit = {}
) {
    val context = LocalContext.current
    val participants = eventViewModel.getParticipantsFromEventId(event.eventId)
        .collectAsState(initial = listOf())
    val wasAddedByCurrentUser = participants.value
        .map { it.id }
        .contains(loggedUser.id)
    TextButton(
        text = if (wasAddedByCurrentUser) "Added" else "Add",
        textColor = if (wasAddedByCurrentUser) Color.Gray else Color.White,
        onClick = {
            addParticipation(eventViewModel)
            addNotification(context = context)
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = !wasAddedByCurrentUser
    )
}

@Composable
private fun DeleteEventButton(
    eventViewModel: EventViewModel,
    onBackToPrevPage: () -> Unit = {}
) {
    val participants = eventViewModel.getParticipantsFromEventId(event.eventId)
        .collectAsState(initial = listOf())
    TextButton(
        text = "Delete",
        textColor = Color.Red,
        onClick = {
            participants.value.forEach { participant ->
                eventViewModel.deleteParticipant(UserAddEventCrossRef(
                    id = participant.id, eventId = event.eventId
                ))
            }
            eventViewModel.deleteEvent(event.eventId)
            onBackToPrevPage()
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

private fun addParticipation(eventViewModel: EventViewModel) {
    val crossRef = UserAddEventCrossRef(id = loggedUser.id, eventId = event.eventId)
    eventViewModel.addParticipant(crossRef)
    event = event.copy(participants = event.participants + 1)
    eventViewModel.updateParticipants(event.participants.toInt(), event.eventId)
}

private fun addNotification(context: Context) {
    val scheduler = NotificationScheduler()
    val calendar = getEventDate()
    val (h, m) = event.starts.split(":").map { it.toInt() }
    calendar.set(Calendar.HOUR_OF_DAY, h)
    calendar.set(Calendar.MINUTE, m)

    Log.d("DELAYED_NOTIF", "test notifica ${calendar.time}")
    scheduler.scheduleNotification(
        context = context,
        scheduledDate = calendar,
        title = "${event.name} is starting!",
        content = "Hurry! ${event.name} is starting soon!"
    )
}

private fun checkEventValid() {
    if (event.name.isEmpty()) {
        throw IllegalStateException("Event name cannot be empty")
    }
    if (getEventDate().before(Calendar.getInstance())) {
        throw IllegalStateException("Event date cannot be in the past")
    }
}

private fun saveNewEvent(
    context: Context,
    eventViewModel: EventViewModel,
    events: List<Event>
) {
    try {
        val newID = events.map { it.eventId }
            .ifEmpty { listOf(0) }
            .max()
            .plus(1)
        event = event.copy(eventId = newID)
        checkEventValid()
        eventViewModel.createNewEvent(event)
        addParticipation(eventViewModel)
        addNotification(context = context)
    } catch (ex: Exception) {
        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
    }
}

private fun isEditingMode(): Boolean {
    return isNewEvent() || isEditing
}

private fun isNewEvent(): Boolean {
    return event.eventId == -1
}

private fun isCreatedByCurrentUser(): Boolean {
    return event.creator.username == loggedUser.username
}

private fun getEventDate(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, event.day.mod(100))
    calendar.set(Calendar.MONTH, (event.day / 100).mod(100))
    calendar.set(Calendar.YEAR, event.day / 10000)
    return calendar
}