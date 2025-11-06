# FakePushNotifApp

## Project Info

- Created at: 2025/11/06
- IDE: Android Studio (version: Narwhal 3 Feature Drop | 2025.1.3)
- Project Template: Android Studio > Empty Activity

## Projects

FakePushNotifAppA (appA)：
Push通知を受信し、アクションを行うアプリ。

FakePushNotifAppB (appB)：
Push通知を発信するアプリ。

## Usage

※FakePushNotifAppA をインストール後、［アプリ情報］から［通知］の権限を許可してください。

- adb (Android Device Bridge) からIntentをbroadcastする。
- 他のアプリ (FakePushNotifAppB) からIntentをbroadcastする。

### adb (Android Device Bridge) からIntentをbroadcastする。

```bash
adb shell am broadcast \
  -a com.kishibashi.androidapp.fakepushnotif_a.ACTION_CHAT_MESSAGE_NOTIFICATION \
  -n com.kishibashi.androidapp.fakepushnotif_a/.ChatNotificationReceiver \
  --es chatRoomId chatroom_123 \
  --es chatMessageId chatmessage_123 \
  --es senderName Bob \
  --es chatMessageText "Hello!"
```

### 他のアプリ (FakePushNotifAppB) からIntentをbroadcastする。

FakePushNotifAppB を起動し、画面のボタンをクリックする。
