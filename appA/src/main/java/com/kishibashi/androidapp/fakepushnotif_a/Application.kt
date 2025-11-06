package com.kishibashi.androidapp.fakepushnotif_a

import android.app.NotificationChannel
import android.app.NotificationManager

class Application : android.app.Application() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "chat_channel"
        const val NOTIFICATION_CHANNEL_NAME = "Chat Notifications"
        const val NOTIFICATION_CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NOTIFICATION_CHANNEL_IMPORTANCE
            )
        )
    }
}
