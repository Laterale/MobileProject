package com.example.partyapp.ui

import LocationHelper
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.partyapp.R
import com.example.partyapp.data.entity.User
import com.example.partyapp.services.ImageChooserService
import com.example.partyapp.services.PermissionsHelper
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.ui.theme.getColorScheme
import com.example.partyapp.viewModel.EventViewModel
import com.example.partyapp.viewModel.UserViewModel
import java.io.File

val labelGray: Color = Color(0x80FFFFFF)
var user: User? = null;

@Composable
fun ProfileScreen(
    onSettingsClicked: () -> Unit,
    onQRScanFABClicked: () -> Unit,
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    session: String,
) {
    SetCurrentUser(userViewModel, session)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(30.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { settingsButton(onSettingsClicked = onSettingsClicked) }
        item { UserProfile(userViewModel = userViewModel) }
    }

    Box (modifier = Modifier.fillMaxSize()) {
        val colorScheme = getColorScheme(userSettings)
        FloatingActionButton(
            onClick = onQRScanFABClicked,
            backgroundColor = colorScheme.surface,
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.QrCodeScanner,
                contentDescription = stringResource(id = R.string.lbl_scan_qr),
                tint = colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun UserProfile(
    userViewModel: UserViewModel,
) {
    UserProfilePic(userViewModel)
    Text(text = user?.username ?: stringResource(id = R.string.username), style = Typography.bodyMedium)
    CityNameDisplay()
    XpBar()
    HorizontalDivider(color = Color.White, modifier = Modifier.padding(vertical = 30.dp))
}

@Composable
private fun settingsButton(
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { onSettingsClicked() }) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = stringResource(id = R.string.settings),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun SetCurrentUser(userViewModel: UserViewModel, session: String) {
    val users by userViewModel.users.collectAsState(initial = listOf())
    if (!isLoggedUser(userViewModel, session)) {
        user = if (userViewModel.loggedUser != null)
            userViewModel.loggedUser
        else if (users.isNotEmpty()) {
            users.find { it.username == session }
        } else users.first()
    }
}

private fun isLoggedUser(userViewModel: UserViewModel, session: String): Boolean {
    return if (user == null) false
    else if (userViewModel.loggedUser != null) user?.username == userViewModel.loggedUser?.username
    else user?.username == session
}

@Composable
private fun XpBar() {
    val userLv = LevelThreshold.getLevelForXp(user?.exp ?: 0).level
    Row(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Text(text = "$userLv", color = Color.White)
        LinearProgressIndicator(
            progress = { LevelThreshold.getPercentageToNextLevel(user?.exp ?: 0) },
            modifier = Modifier.size(200.dp, 2.5.dp),
            color = Color.White,
            trackColor = Color.DarkGray,
        )
        Text(text = "${userLv+1}", color = Color.White)
    }
}

@Composable
private fun UserProfilePic(userViewModel: UserViewModel) {
    var photoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }
    val setImg: (Uri, String) -> Unit = { uri, path ->
        photoUri = uri
        user!!.pfp = path
        userViewModel.changePfpFromId(user?.id!!, path)
    }

    Box(
        modifier = Modifier.size(160.dp,150.dp)
    ) {
        if (photoUri == Uri.EMPTY && user != null) {
            photoUri = Uri.parse(user?.pfp)
        }
        AsyncImage(
            model = photoUri,
            contentDescription = stringResource(id = R.string.lbl_user_pfp),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .align(Alignment.Center)
        )
        AddImageBtn(
            onImageChosen = setImg,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
private fun AddImageBtn(
    onImageChosen: (Uri, String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val errPermDenied = stringResource(id = R.string.err_perm_denied)
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
            Toast.makeText(context, errPermDenied, Toast.LENGTH_SHORT).show()
        }
    }

    SmallFloatingActionButton(
        onClick = { showDialog = true },
        modifier = modifier,
        containerColor = Color.hsl(0f, 0f, 1f, 0.90f),
        shape = RoundedCornerShape(40)
    ) {
        Icon(
            imageVector = Icons.Filled.AddAPhoto,
            contentDescription = stringResource(id = R.string.icon_add),
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
    if (showDialog) {
        imgChooser.ChooseImage(
            imagePickerLauncher = imagePickerLauncher,
            cameraPermissionRequest = cameraPermissionRequest,
            onDismissRequest = { showDialog = false }
        )
    }
}

@Composable
private fun CityNameDisplay() {
    val context = LocalContext.current
    val helper = LocationHelper(context)
    var cityName: String?  by remember { mutableStateOf(null) }

    PermissionsHelper(context).RequestLocationPermission {
        helper.getCurrentLocation { loc ->
            if (loc != null) {
                cityName = helper.getCityName(context, loc.latitude, loc.longitude)
            } else {
                cityName = null
            }
        }
    }

    Row {
        Icon(
            imageVector = if (cityName != null) Icons.Filled.LocationOn else Icons.Filled.LocationOff,
            contentDescription = if (cityName != null) stringResource(id = R.string.user_location)
                                 else stringResource(id = R.string.no_location),
            tint = labelGray,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = if (cityName != null) cityName.toString()
                   else stringResource(id = R.string.no_location),
            style = Typography.labelMedium
        )
    }
}
