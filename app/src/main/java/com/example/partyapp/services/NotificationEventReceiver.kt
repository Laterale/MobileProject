package com.example.partyapp.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationEventReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val helper = NotificationHelper(context)
        val title: String? = intent.getStringExtra("TITLE")
        val content: String? = intent.getStringExtra("CONTENT")
        Log.d("NOTIF_SHOW", "Notification received")
        helper.showSimpleNotification(title ?: "", content ?: "")
    }
}