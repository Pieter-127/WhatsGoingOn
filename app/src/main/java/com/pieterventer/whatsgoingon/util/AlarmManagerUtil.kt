package com.pieterventer.whatsgoingon.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.pieterventer.whatsgoingon.util.notification.NotificationScheduler
import java.util.*

class AlarmManagerUtil(private val context: Context) {

    fun scheduleNotifications() {

        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        val alarmIntent = Intent(context, NotificationScheduler::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        alarmManager?.setInexactRepeating(
            AlarmManager.RTC, determineAlarmStartTime(),
            AlarmManager.INTERVAL_HOUR, pendingIntent
        )
    }

    //returns the time in long for tomorrow at 07:00
    private fun determineAlarmStartTime(): Long {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)

        return calendar.timeInMillis
    }
}