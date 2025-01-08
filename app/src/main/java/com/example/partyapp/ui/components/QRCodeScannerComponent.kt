package com.example.partyapp.ui.components

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    modifier: Modifier = Modifier,
    onScanResult: (String) -> Unit = {},
    onlyIcon: Boolean = true,
) {
    var showScanner by remember { mutableStateOf(false) }

    if (onlyIcon) {
        IconButton(onClick = { showScanner = !showScanner }, modifier = modifier) {
            Icon(
                imageVector = Icons.Default.QrCodeScanner,
                contentDescription = stringResource(id = R.string.lbl_scan_qr),
                tint = Color.White
            )
        }
    } else {
        PartyIconButton(
            icon = Icons.Default.QrCodeScanner,
            contentDescription = stringResource(id = R.string.lbl_scan_qr),
            text = if (!showScanner) stringResource(id = R.string.lbl_scan_qr)
            else stringResource(id = R.string.lbl_scan_qr_hide),
            onClick = { showScanner = !showScanner },
            modifier = modifier.fillMaxWidth()
        )
    }
    if (showScanner) {
        Scanner(onScanResult = onScanResult, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun Scanner(
    modifier: Modifier = Modifier,
    onScanResult: (String) -> Unit = {},
) {
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(false) }
    val permissionsHelper = PermissionsHelper(context)
    Box (
        modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(
                color = Indigo
                    .copy(alpha = 0.7f)
                    .compositeOver(Color.White.copy(alpha = 0.3f))
            )
    ) {
        permissionsHelper.RequestCameraPermission(doIfGranted = { hasPermission = true })
        if (hasPermission) {
            ScannerPreview(onScanResult = onScanResult, modifier = Modifier.fillMaxSize())
        } else {
            Text(text = stringResource(id = R.string.err_perm_denied), color = Color.Gray)
        }
    }
}

@Composable
private fun ScannerPreview(
    modifier: Modifier = Modifier,
    onScanResult: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                val analyzer = QRCodeAnalyzer(context, onResult = { onScanResult(it) })

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
        modifier = modifier
    )
}

