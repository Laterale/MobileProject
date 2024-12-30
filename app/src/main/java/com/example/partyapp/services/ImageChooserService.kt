package com.example.partyapp.services

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.partyapp.BuildConfig
import com.example.partyapp.R
import com.example.partyapp.ui.components.PartyDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImageChooserService {

    @Composable
    fun ChooseImage(
        imagePickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
        cameraPermissionRequest: ManagedActivityResultLauncher<String, Boolean>,
        onDismissRequest: () -> Unit
    ) {
        this.ShowChooseImageDialog(
            title = stringResource(id = R.string.choose_pfp),
            onPickImage = {
                imagePickerLauncher.launch(PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                ))
            },
            onTakePic = { cameraPermissionRequest.launch(Manifest.permission.CAMERA) },
            onDismissRequest = onDismissRequest
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ShowChooseImageDialog(
        title: String,
        onPickImage: () -> Unit,
        onTakePic: () -> Unit,
        onDismissRequest: () -> Unit
    ) {
        PartyDialog(title, {
            Surface(
                onClick = {
                    onTakePic.invoke()
                    onDismissRequest.invoke()
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                color = Color.Transparent
            ) { Text(stringResource(id = R.string.take_pic), color = Color.White) }
            Surface(
                onClick = {
                    onPickImage.invoke()
                    onDismissRequest.invoke()
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                color = Color.Transparent
            ) { Text(stringResource(id = R.string.choose_image), color = Color.White) }
        }, onDismissRequest)
    }

    fun getTempImageUri(context: Context): Uri {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_$timeStamp" + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(imageFileName, ".jpg", storageDir)
        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", image)
    }

    // Helper function to save the image to internal storage
    fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.filesDir, "profile_pic_${System.currentTimeMillis()}.jpg")
            inputStream.use { input ->
                file.outputStream().use { output ->
                    input?.copyTo(output)
                }
            }
            file.absolutePath // Return the saved file path
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun addPhotoToGallery(context: Context, file: File) {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DATA, file.absolutePath) // Deprecated after API 28
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
        }
        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (uri == null) {
            Log.e("GalleryUpdate", "Failed to add photo to gallery")
        }
    }


}