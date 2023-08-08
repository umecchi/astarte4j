package jp.umecchi.astarte4j.api.stream

import com.google.gson.Gson
import jp.umecchi.astarte4j.api.entities.status.Data
import jp.umecchi.astarte4j.api.entities.status.NotificationData
import jp.umecchi.astarte4j.api.entities.status.NotificationMessage
import okhttp3.*
import okio.ByteString
import org.json.JSONObject

class AstarteWebSocketListener(
    val handler: Handler,
    val listener: StreamListener,
    val gson: Gson
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("WebSocket opened successfully")
        listener.onConnect()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("Received text message: $text")
        handler.onMessage(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        println("Received binary message: ${bytes.utf8()}")
        handler.onMessage(bytes.utf8())
        val j = JSONObject(bytes.utf8())
        println("JSONObject: $j")
        val id = j.getString("id")
        val from = j.getString("from")
        val data_type = j.getString("data_type")
        val data = j.getJSONObject("data")
        if (data_type == "Text") {
            val text = data.getString("Text")
            handler.onText(text)
        }
        if (data_type == "StatusData") {
            val StatusData = data.getString("StatusData")
            val status = gson.fromJson(
                StatusData,
                Data::class.java
            )
            handler.onStatus(status)
        }
        if (data_type == "NotificationStatus") {
            val NotificationStatus = data.getString("NotificationStatus")
            val notification = gson.fromJson(
                NotificationStatus,
                NotificationData::class.java
            )
            handler.onNotification(notification)
        }
        if (data_type == "NotificationMessage") {
            val Message = data.getString("NotificationMessage")
            val notificationMessage = gson.fromJson(
                Message,
                NotificationMessage::class.java
            )
            handler.onNotificationMessage(notificationMessage)
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("Connection closed: $code $reason")
        listener.onDisconnect()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        val message = "Connection failed: ${t.localizedMessage}"
        println(message)
        handler.onMessage(message)
    }
}