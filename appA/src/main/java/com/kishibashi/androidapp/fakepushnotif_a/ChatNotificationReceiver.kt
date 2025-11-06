package com.kishibashi.androidapp.fakepushnotif_a

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.kishibashi.androidapp.fakepushnotif_a.Application.Companion.NOTIFICATION_CHANNEL_ID

class ChatNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ChatNotificationReceiver", "onReceive")
        if (
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("ChatNotificationReceiver", "No permission")
            return
        }

        val chatRoomId = intent.getStringExtra("chatRoomId") ?: return
        val chatMessageId = intent.getStringExtra("chatMessageId") ?: ""
        val senderName = intent.getStringExtra("senderName") ?: ""
        val chatMessageText = intent.getStringExtra("chatMessageText") ?: ""

        val openIntent = Intent(context, MainActivity::class.java).apply {
            action = "${context.applicationContext.packageName}.ACTION_OPEN_CHAT_MESSAGE"
            putExtra("chatRoomId", chatRoomId)
            putExtra("chatMessageId", chatMessageId)
            putExtra("senderName", senderName)
            putExtra("chatMessageText", chatMessageText)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            openIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle(senderName)
            .setContentText(chatMessageText)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(context)
            .notify(System.currentTimeMillis().toInt(), notification)
    }
}
