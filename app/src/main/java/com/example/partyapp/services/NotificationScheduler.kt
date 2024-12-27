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
        val intent = Intent(context.applicationContext, NotificationEventReceiver::class.java)
        intent.putExtra("TITLE", title)
        intent.putExtra("CONTENT", content)
        val pendingIntent = PendingIntent.getBroadcast(
            context.applicationContext,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val selectedDate = Calendar.getInstance().apply {
//            timeInMillis = datePickerState.selectedDateMillis!!
//        }
//        val year = selectedDate.get(Calendar.YEAR)
//        val month = selectedDate.get(Calendar.MONTH)
//        val day = selectedDate.get(Calendar.DAY_OF_MONTH)
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day, timePickerState.hour, timePickerState.minute)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            scheduledDate.timeInMillis,
            pendingIntent
        )

    }
}