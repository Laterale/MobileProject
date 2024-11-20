package com.example.partyapp.ui

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.partyapp.BuildConfig
import com.example.partyapp.data.entity.User
import com.example.partyapp.ui.components.PartyDialog
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.SettingsViewModel
import com.example.partyapp.viewModel.UserViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


var user: User? = null

@Composable
fun ProfileScreen(
    onEventClicked: ()->Unit,
    onSettingsClicked: ()->Unit,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel,
    session: String,
) {
    SetCurrentUser(userViewModel, session)
    /*
    val context = LocalContext.current
    val users by userViewModel.users.collectAsState(initial = listOf())
    val currentTheme = settingsViewModel.theme.collectAsState(initial = "Light").value
    if(users.isNotEmpty() || userViewModel.loggedUser != null) {
        val loggedUser = if (userViewModel.loggedUser == null)
            users.find { it.username == session }!! else userViewModel.loggedUser!!*/

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(30.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.Start),
                onClick = { onSettingsClicked() }
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
            UserProfilePic(userViewModel)
            Text(text = user?.username ?: "Username", style = Typography.labelMedium)
            Text(text = "City", style = Typography.labelSmall)
            Text(text = "Age", style = Typography.labelSmall)
            XpBar()
            Divider(
                color = Color.White
            )
        }
    /*}*/
}

@Composable
fun SetCurrentUser(userViewModel: UserViewModel, session: String) {
    val users by userViewModel.users.collectAsState(initial = listOf())
    if (user == null && users.isNotEmpty()) {
        user = if (userViewModel.loggedUser != null) userViewModel.loggedUser
        else if (session != "") users.find { it.username == session }
        else users.first()
    }

    Log.println(Log.WARN, "USERS_DB", users.size.toString())
    Log.println(Log.WARN, "USERS_SESSION", session)
    Log.println(Log.WARN, "USERS_LOGGED", userViewModel.loggedUser.toString())
}

@Composable
fun XpBar() {
    Row(
        modifier = Modifier.padding(0.dp,15.dp,0.dp,30.dp)
    ) {
        LinearProgressIndicator(
            progress = 0.5f,
            color = Color.White,
            trackColor = Color.DarkGray,
            modifier = Modifier.size(200.dp, 2.5.dp)
        )
    }
}

fun getTempImageUri(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
    val imageFileName = "IMG_" + timeStamp + "_"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image: File = File.createTempFile(imageFileName, ".jpg", storageDir)
    return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", image)
}

fun changeProfilePic(userViewModel: UserViewModel, newPhoto: Uri) {
    val userId: Int = user?.id!!
    userViewModel.changePfpFromId(userId, newPhoto.toString())
}

@Composable
fun UserProfilePic(userViewModel: UserViewModel) {
    val context = LocalContext.current
    var photoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }
    var tempPhotoUri: Uri by remember { mutableStateOf(value = Uri.EMPTY) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            photoUri = uri
            changeProfilePic(userViewModel, uri)
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            photoUri = tempPhotoUri
            changeProfilePic(userViewModel, tempPhotoUri)
        }
    }
    val cameraPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            tempPhotoUri = getTempImageUri(context)
            cameraLauncher.launch(tempPhotoUri)
        }
        // else Permission Denied
    }

    Box(
        modifier = Modifier.size(160.dp,150.dp)
    ) {
        AsyncImage(                            //profile picture
            model = photoUri,
            contentDescription = "Profile image",
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .align(Alignment.Center)
        )
        AddImageBtn(
            onPickImage = {
                imagePickerLauncher.launch(PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                ))
            },
            onTakePic = { cameraPermissionRequest.launch(Manifest.permission.CAMERA) },
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
fun AddImageBtn(
    onPickImage: () -> Unit,
    onTakePic: () -> Unit,
    modifier: Modifier
) {
    var showDialog: Boolean by remember { mutableStateOf(false) }
    SmallFloatingActionButton(
        onClick = { showDialog = true },
        modifier = modifier,
        containerColor = Color.hsl(0f, 0f, 1f, 0.90f),
        shape = RoundedCornerShape(40)
    ) {
        Icon(
            imageVector = Icons.Filled.AddAPhoto,
            contentDescription = "Edit profile",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
    if (showDialog) {
        ShowChooseImageDialog(
            onPickImage = onPickImage,
            onTakePic = onTakePic,
            onDismissRequest = { showDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowChooseImageDialog(
    onPickImage: () -> Unit,
    onTakePic: () -> Unit,
    onDismissRequest: () -> Unit
) {
    PartyDialog("Choose new profile picture", {
        Surface(
            onClick = {
                onTakePic.invoke()
                onDismissRequest.invoke()
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            color = Color.Transparent
        ) { Text("Take a picture", color = Color.White) }
        Surface(
            onClick = {
                onPickImage.invoke()
                onDismissRequest.invoke()
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            color = Color.Transparent
        ) { Text("Choose from gallery", color = Color.White) }
    }, onDismissRequest)
}
