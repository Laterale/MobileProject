package com.example.partyapp.ui.components

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.partyapp.R
import com.example.partyapp.services.PermissionsHelper
import com.example.partyapp.services.QRCodeAnalyzer
import com.example.partyapp.ui.theme.Indigo


@Composable
fun QRCodeScanner(
    onScanResult: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var hasPermission by remember { mutableStateOf(false) }
    val permissionsHelper = PermissionsHelper(context)

    IconButton(
        icon = Icons.Default.QrCodeScanner,
        contentDescription = stringResource(id = R.string.lbl_scan_qr),
        text = stringResource(id = R.string.lbl_scan_qr),
        onClick = { showDialog = !showDialog },
        modifier = Modifier.fillMaxWidth()
    )

    if (showDialog) {
//        PartyDialog(
//            title = stringResource(id = R.string.lbl_scan_qr),
//            content = {
        Box (
            Modifier.clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .background(
                    color = Indigo.copy(alpha = 0.7f)
                        .compositeOver(Color.White.copy(alpha = 0.3f))
                )
        ) {
            permissionsHelper.RequestCameraPermission(
                doIfGranted = {
                    Log.d("PERMS", "ok")
                    hasPermission = true
                },
                elseIfDenied = { Log.d("PERMS", "no") }
            )
            if (hasPermission) {
                Scanner(onScanResult)
            } else {
                Text(text = stringResource(id = R.string.err_perm_denied), color = Color.Gray)
            }
        }
//            },
//            onDismissRequest = { showDialog = false }
//        )
    }


}

@Composable
private fun Scanner(onScanResult: (String) -> Unit = {}) {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                val analyzer = QRCodeAnalyzer(context,
                    onResult = { result ->
                        Log.d("QRCodeAnalyzer", "Scanned QR Code: $result")
                    }
                )

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(ContextCompat.getMainExecutor(ctx), analyzer)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    ctx as LifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
                preview.setSurfaceProvider(this.surfaceProvider)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

