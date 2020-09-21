package com.pieterventer.whatsgoingon.util.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pieterventer.whatsgoingon.R
import com.pieterventer.whatsgoingon.ui.dashboard.DashboardActivity
import java.util.*

class NotificationScheduler : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (shouldShowNotification()) {
            val dashboardIntent = Intent(context, DashboardActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(
                context, 0, dashboardIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )

            val builder =
                NotificationCompat.Builder(context, DashboardActivity.NOTIFICATION_CHANNEL)
                    .setSmallIcon(R.drawable.ic_notification_drawable)
                    .setContentTitle(context.getString(R.string.new_notifications_title))
                    .setContentText(context.getString(R.string.new_notifications_content))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(context)) {
                notify(1, builder.build())
            }
        }
    }

    private fun shouldShowNotification(): Boolean {

        val calendarInstance = Calendar.getInstance()

        val currentTime = calendarInstance.time

        calendarInstance.set(Calendar.HOUR_OF_DAY, 6)
        val minimumNotificationTime = calendarInstance.time

        calendarInstance.set(Calendar.HOUR_OF_DAY, 18)
        val maximumNotificationTime = calendarInstance.time

        return currentTime > minimumNotificationTime && currentTime < maximumNotificationTime
    }

}