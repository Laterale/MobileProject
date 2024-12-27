package com.example.partyapp.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationEventReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val scheduleNotificationService = context?.let { NotificationHelper(it) }
        val title: String? = intent?.getStringExtra("TITLE")
        scheduleNotificationService?.showSimpleNotification(title ?: "", "")
    }
}