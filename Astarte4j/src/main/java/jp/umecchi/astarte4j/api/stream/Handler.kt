package jp.umecchi.astarte4j.api.stream

import jp.umecchi.astarte4j.api.entities.status.Data
import jp.umecchi.astarte4j.api.entities.status.NotificationData

interface Handler {
    fun onMessage(string: String)

    fun onText(string: String)

    fun onStatus(data: Data)

    fun onNotification(notificationData: NotificationData)
}