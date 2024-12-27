package com.example.partyapp.services

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.partyapp.R
import kotlin.random.Random

class NotificationHelper(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    fun showSimpleNotification(title: String, content: String) {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.logo_icon)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }
}