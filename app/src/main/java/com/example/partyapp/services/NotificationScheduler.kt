package com.example.partyapp.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

class NotificationScheduler {
    fun scheduleNotification(
        context: Context,
        scheduledDate: Calendar,
        title: String,
        content: String
    ) {
        val intent = Intent(context, NotificationEventReceiver::class.java)
        intent.putExtra("TITLE", title)
        intent.putExtra("CONTENT", content)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            scheduledDate.timeInMillis,
            pendingIntent
        )

    }
}