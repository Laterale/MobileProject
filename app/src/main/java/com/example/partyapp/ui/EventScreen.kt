package com.example.partyapp.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.data.LocationDetails
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.entity.User
import com.example.partyapp.data.relation.UserAddEventCrossRef
import com.example.partyapp.services.EventFactory
import com.example.partyapp.services.EventUtilities
import com.example.partyapp.services.ImageChooserService
import com.example.partyapp.services.NotificationScheduler
import com.example.partyapp.services.PermissionsHelper
import com.example.partyapp.ui.components.AddButton
import com.example.partyapp.ui.components.LocationPickerDialogButton
import com.example.partyapp.ui.components.PartyDatePickerComponent
import com.example.partyapp.ui.components.PartyDialog
import com.example.partyapp.ui.components.PartyIconButton
import com.example.partyapp.ui.components.PartyTextField
import com.example.partyapp.ui.components.PartyTimePickerComponent
import com.example.partyapp.ui.components.QRDialogButton
import com.example.partyapp.ui.components.TextButton
import com.example.partyapp.ui.theme.Glass10
import com.example.partyapp.ui.theme.Glass20
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.ZoneId
import java.util.Calendar

private val factory = EventFactory()
private var event: Event = factory.createEmpty()
private lateinit var loggedUser: User
private lateinit var viewModel: EventViewModel
private var isEditing: Boolean = false
private var selectedDateNumber: Int = 0

@Composable
fun EventScreen(
    session: String,
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onBackToPrevPage: () -> Unit = {}
) {
    event = eventViewModel.eventSelected ?: factory.createEmptyEvent(userViewModel.loggedUser!!)
    loggedUser = userViewModel.loggedUser!!
    viewModel = eventViewModel
    val permissionsHelper = PermissionsHelper(LocalContext.current)
    permissionsHelper.RequestPermission(permission = Manifest.permission.WRITE_CALENDAR)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(vertical = 10.dp, horizontal = 30.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        EventView(modifier = Modifier.weight(0.9f))
        Actions(
            eventViewModel = eventViewModel,
            userViewModel = userViewModel,
            onBackToPrevPage = onBackToPrevPage,
        )
    }
}

@Composable
private fun EventView(modifier: Modifier = Modifier) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        item { EventImage(modifier = Modifier
            .height(200.dp)) }
        item { EventTitle() }
        item { EventAuthor() }
        item { HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp), color = Color.White) }
        item { EventDetails(modifier = Modifier
            .fillMaxWidth()
//            .height(120.dp)
        ) }
        item { EventDescription(modifier = Modifier.height(300.dp)) }
    }
}

@Composable
private fun EventImage(modifier: Modifier = Modifier) {
    var photoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }
    val setImg: (Uri, String) -> Unit = { uri, path ->
        photoUri = uri
        updateEvent(event.copy(image = path))
    }

    if (isEditingMode() && photoUri == Uri.EMPTY) {
        AddEventImageBtn(
            onImageChosen = setImg,
            modifier = modifier.fillMaxWidth()
        )
    } else {
        AsyncImage(
            model = event.image,
            contentDescription = stringResource(id = R.string.lbl_event_image),
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
//    Row (modifier = modifier) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(5.dp),
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            item { EventDateDetail() }
//            item { EventLocationDetail() }
//            item { EventTimeDetail() }
//            item { EventParticipants() }
//        }
//    }
    /* Eventually put everything in column. I think it looks better but
     * we've already submitted the prototype so I don't know if we can change it.
     */


    Row (modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            EventDateDetail()
            EventLocationDetail()
            EventTimeDetail()
            EventParticipants()
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
                    updateEvent(event.copy(name = it))
                },
                placeholder = stringResource(id = R.string.lbl_event_name),
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
            contentDescription = stringResource(id = R.string.lbl_creator_pfp),
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
    Column(modifier = modifier) {
        if (isEditingMode()) {
            var des: String by remember { mutableStateOf(event.description) }
            PartyTextField(
                value = des,
                onValueChange = {
                    des = it
                    updateEvent(event.copy(description = it))
                },
                placeholder = stringResource(id = R.string.description),
                modifier = Modifier.fillMaxSize(),
                singleLine = false
            )
        } else {
            OutlinedCard(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Glass10),
                border = BorderStroke(1.dp, Glass20)
            ) {
                Text(
                    text = event.description,
                    style = Typography.bodyMedium,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(10.dp),
                    textAlign = TextAlign.Justify
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
            contentDescription = stringResource(id = R.string.lbl_event_day),
            tint = Color.White
        )
        if (isEditingMode()) {
            val calendar = Calendar.getInstance()
            if (event.day < EventUtilities().BASE_DATE_INT) {
                val dayValue = calendar.get(Calendar.YEAR) * 10000 + calendar.get(Calendar.MONTH) * 100 + calendar.get(Calendar.DAY_OF_MONTH)
                updateEvent(event.copy(day = dayValue))
                selectedDateNumber = event.day
            }
            var date: String by remember { mutableStateOf(dateToStr(EventUtilities().getEventStartDateTime(event))) }
            PartyDatePickerComponent(
                text = date,
                onDatePicked = { year, month, day ->
                    calendar.apply { set(year, month, day) }
                    date = dateToStr(calendar)
                    updateEvent(event.copy(day = year * 10000 + month * 100 + day))
                    selectedDateNumber = event.day
                }
            )
        } else {
            val calendar = EventUtilities().getEventStartDateTime(event)
            Text(text = dateToStr(calendar),color = Color.White)
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
            contentDescription = stringResource(id = R.string.lbl_event_time),
            tint = Color.White
        )
        if (isEditingMode()) {
            var starts: String by remember { mutableStateOf(event.starts) }
            var ends: String by remember { mutableStateOf(event.ends) }
            PartyTimePickerComponent(
                text = starts,
                onTimePicked = { h, m ->
                    starts = "%02d:%02d".format(h, m)
                    ends = "%02d:%02d".format((h + 2) % 24, m)
                    updateEvent(event.copy(starts = starts, ends = ends))
                }
            )
            Text(text = "-", color = Color.White)
            PartyTimePickerComponent(
                text = ends,
                onTimePicked = { h, m ->
                    ends = "%02d:%02d".format(h, m)
                    updateEvent(event.copy(ends = ends))
                }
            )
        } else {
            Text(text = event.starts + "-" + event.ends, color = Color.White)
        }
    }
}

@Composable
private fun EventLocationDetail() {
    val chooseLocation = stringResource(id = R.string.choose_location)
    val context = LocalContext.current
    var displayedText by remember { mutableStateOf(chooseLocation) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.AddLocation,
            contentDescription = stringResource(id = R.string.lbl_event_location),
            tint = Color.White
        )
        if (isEditingMode()) {
            LocationPickerDialogButton(
                text = displayedText,
                onLocationPicked = { address ->
                    updateEvent(event.copy(location = LocationDetails(
                        latitude = address.latitude,
                        longitude = address.longitude,
                        state = address.countryName,
                        city = address.locality ?: "",
                        street = "${address.thoroughfare ?: "---"}, ${address.subThoroughfare ?: "---"}"
                    )))
                    displayedText = EventUtilities().getEventLocationString(context, event)
                }
            )
        } else {
            displayedText = EventUtilities().getEventLocationString(context, event)
            Text(text = displayedText, color = Color.White)
        }
    }
}

@Composable
private fun EventParticipants() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(
//            top = if (isEditingMode()) 0.dp else 5.dp,
//            bottom = if (isEditingMode()) 15.dp else 0.dp
//        )
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(id = R.string.lbl_event_participants),
            tint = Color.White
        )
        Text(text = event.participants.toString(), color = Color.White)
    }
}

@Composable
private fun Actions(
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    onBackToPrevPage: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (!EventUtilities().isEventCreatedBy(event, loggedUser)) {
            AddEventButton(eventViewModel, onEventAdd = { showDialog = true })
        } else if (EventUtilities().isNewEvent(event)) {
            SaveDiscardBtns(
                eventViewModel, userViewModel,
                onEventSave = { showDialog = true }
            )
        } else {
            DeleteEventButton(eventViewModel, onBackToPrevPage)
        }
    }
    if (showDialog) {
        AddToCalendarDialog(
            onHideDialog = { showDialog = false },
            onBackToPrevPage = onBackToPrevPage,
            onEventAdded =  {
                if (EventUtilities().isNewEvent(event)) onBackToPrevPage()
            }
        )
    }
}

@Composable
private fun SaveDiscardBtns(
    eventViewModel: EventViewModel,
    userViewModel: UserViewModel,
    onBackToPrevPage: () -> Unit = {},
    onEventSave: () -> Unit = {}
) {
    val context = LocalContext.current
    val events = eventViewModel.events.collectAsState(initial = listOf()).value
    val newEventXp = 10
    val xpGainedMsg = stringResource(id = R.string.msg_gained_xp, newEventXp)
    PartyIconButton(
        icon = Icons.Default.Close, contentDescription = stringResource(id = R.string.discard),
        textColor = Color.Red,
        onClick = onBackToPrevPage,
        modifier = Modifier.fillMaxWidth(0.5f),
    )
    PartyIconButton(
        icon = Icons.Default.Check, contentDescription = stringResource(id = R.string.save),
        textColor = Color.Green,
        onClick = {
            try {
                EventUtilities().checkEventValid(event)
                val newID = events.map { it.eventId }
                    .ifEmpty { listOf(0) }
                    .max()
                    .plus(1)
                updateEvent(event.copy(eventId = newID))
                eventViewModel.createNewEvent(event)
                userViewModel.addExpToLoggedUser(newEventXp)
                addParticipation(context, eventViewModel)
                addNotification(context = context)
                Toast.makeText(context, xpGainedMsg, Toast.LENGTH_SHORT).show()
                onEventSave()
                onBackToPrevPage()
            } catch (ex: Exception) {
                Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AddEventButton(
    eventViewModel: EventViewModel,
    onEventAdd: () -> Unit = {}
) {
    val context = LocalContext.current
    val participants = eventViewModel.getParticipantsFromEventId(event.eventId)
        .collectAsState(initial = listOf())
    val wasAddedByCurrentUser = participants.value
        .map { it.id }
        .contains(loggedUser.id)
    TextButton(
        text = if (wasAddedByCurrentUser) stringResource(id = R.string.added)
               else stringResource(id = R.string.add),
        textColor = if (wasAddedByCurrentUser) Color.Gray else Color.White,
        onClick = {
            addParticipation(context, eventViewModel)
            addNotification(context = context)
            onEventAdd()
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

    PartyIconButton(
        icon = Icons.Default.DeleteForever,
        contentDescription = stringResource(id = R.string.delete),
        textColor = Color.Red,
        onClick = {
            participants.value.forEach { participant ->
                eventViewModel.deleteParticipant(
                    UserAddEventCrossRef(
                        id = participant.id, eventId = event.eventId
                    )
                )
            }
            eventViewModel.deleteEvent(event.eventId)
            onBackToPrevPage()
        },
        modifier = Modifier.fillMaxWidth(0.5f),
    )
    QRDialogButton(
        text = stringResource(id = R.string.event_qr_code),
        qrContent = Json.encodeToString(event),
        modifier = Modifier.fillMaxWidth()
    )
}

private fun addParticipation(context: Context, eventViewModel: EventViewModel) {
    val crossRef = UserAddEventCrossRef(id = loggedUser.id, eventId = event.eventId)
    eventViewModel.addParticipant(crossRef)
    updateEvent(event.copy(participants = event.participants + 1))
    eventViewModel.updateParticipants(event.participants.toInt(), event.eventId)
}

@Composable
private fun AddToCalendarDialog(
    onHideDialog: () -> Unit = {},
    onBackToPrevPage: () -> Unit = {},
    onEventAdded: () -> Unit = {}
) {
    val context = LocalContext.current
    // Launcher to handle the result of the calendar intent
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK || result.resultCode == Activity.RESULT_CANCELED) {
            onEventAdded() // Call the function after returning from the calendar app
        }
    }

    PartyDialog(title = stringResource(id = R.string.add_to_calendar) + "?",
        onDismissRequest = onHideDialog,
        content = {
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                TextButton(
                    text = stringResource(id = R.string.confirm),
                    onClick = {
                        addToCalendar(context = context, event, launcher)
                    }
                )
                TextButton(
                    text = stringResource(id = R.string.cancel),
                    onClick = {
                        onHideDialog()
                        onBackToPrevPage()
                    }
                )
            }
        }
    )
}

private fun addToCalendar(
    context: Context,
    event: Event,
    launcher:  ManagedActivityResultLauncher<Intent, ActivityResult>?
) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, event.name)
        val location = EventUtilities().getEventLocationString(context, event)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)
        putExtra(CalendarContract.Events.DESCRIPTION, event.description)

        val start = EventUtilities().getEventStartDateTime(event)
        val end = EventUtilities().getEventEndDateTime(event)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.timeInMillis)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.timeInMillis)
    }

    // Verify that there is a calendar app to handle the intent
    if (intent.resolveActivity(context.packageManager) != null) {
        if (launcher != null) {
            launcher.launch(intent)
        } else {
            context.startActivity(intent)
        }
    } else {
        Toast.makeText(context, "No calendar app found!", Toast.LENGTH_SHORT).show()
    }
}

private fun addNotification(context: Context) {
    val scheduler = NotificationScheduler()
    val calendar = EventUtilities().getEventStartDateTime(event)
    Log.d("DELAYED_NOTIF", "Set notification: ${calendar.time}, ${event.name} ")
    scheduler.scheduleNotification(
        context = context,
        scheduledDate = calendar,
        title = "${event.name} is starting!",
        content = "Hurry! ${event.name} is starting soon!"
    )
}

private fun isEditingMode(): Boolean {
    return EventUtilities().isNewEvent(event) || isEditing
}

private fun updateEvent(updated: Event) {
    event = updated
    viewModel.selectEvent(event)
}