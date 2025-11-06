package com.kishibashi.androidapp.fakepushnotif_a

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kishibashi.androidapp.fakepushnotif_a.ui.chat.ChatData
import com.kishibashi.androidapp.fakepushnotif_a.ui.chat.ChatScreen
import com.kishibashi.androidapp.fakepushnotif_a.ui.theme.AppTheme
import com.kishibashi.androidapp.fakepushnotif_a.ui.top.TopScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setContent(intent)
    }

    private fun setContent(intent: Intent?) {
        Log.d("MainActivity", "setContent: ${intent?.action ?: "(null)"}")

        setContent {
            AppTheme {
                when (intent?.action) {
                    "${packageName}.ACTION_OPEN_CHAT_MESSAGE" ->
                        ChatScreen(extractChatIntentData(intent))

                    else -> TopScreen()
                }
            }
        }
    }

    private fun extractChatIntentData(intent: Intent): ChatData {
        return ChatData(
            chatRoomId = intent.getStringExtra("chatRoomId") ?: "(null)",
            chatMessageId = intent.getStringExtra("chatMessageId") ?: "(null)",
            senderName = intent.getStringExtra("senderName") ?: "(null)",
            chatMessageText = intent.getStringExtra("chatMessageText") ?: "(null)",
        )
    }
}
