package com.example.partyapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.services.QRCodeAnalyzer

@Composable
fun QRDialogButton(
    text: String,
    qrContent: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val qrAnalyzer = QRCodeAnalyzer(context)
    val qrCodeBitmap = qrAnalyzer.generateQRCode(qrContent)
    var showDialog: Boolean by remember { mutableStateOf(false) }

    IconButton(
        icon = Icons.Filled.QrCode,
        contentDescription = stringResource(id = R.string.qr),
        onClick = { showDialog = true }, modifier = modifier
    )

    if (showDialog) {
        PartyDialog(
            title = text,
            onDismissRequest = { showDialog = false },
            content = {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp, horizontal = 20.dp),
                ) {
                    qrCodeBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = stringResource(id = R.string.qr),
                            modifier = Modifier.size(300.dp)
                        )
                    }
                }
            }
        )
    }
}