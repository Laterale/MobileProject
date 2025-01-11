package com.example.partyapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.partyapp.R
import com.example.partyapp.data.entity.Event
import com.example.partyapp.data.relation.UserScansEventCrossRef
import com.example.partyapp.ui.components.Scanner
import com.example.partyapp.ui.theme.Typography
import com.example.partyapp.viewModel.EventViewModel
import kotlinx.serialization.json.Json

@Composable
fun ScanScreen(
    eventViewModel: EventViewModel,
    onBackToPrevPage: () -> Unit,
) {
    val context = LocalContext.current
    val newScanXp = 15
    val added = stringResource(id = R.string.added)
    val xpGainedMsg = stringResource(id = R.string.msg_gained_xp, newScanXp)
    var scannedResult by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            text = stringResource(id = R.string.lbl_scan_qr_hint),
            color = Color.White,
            style = Typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp),
        )
        // Show text as debug
//        Text(text = "scanned: (${scannedResult})", color = Color.White)
        Scanner(
            onScanResult = {
                scannedResult = it
                try {
                    val event = Json.decodeFromString<Event>(it)
                    val crossRef = UserScansEventCrossRef(id = user!!.id, eventId = event.eventId)
                    eventViewModel.addScan(crossRef)
                    Toast.makeText(context, "${event.name} $added", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, xpGainedMsg, Toast.LENGTH_SHORT).show()
                } catch (_: Exception) {}
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}