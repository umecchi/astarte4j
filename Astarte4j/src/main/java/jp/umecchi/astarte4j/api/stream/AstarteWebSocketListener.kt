package jp.umecchi.astarte4j.api.stream

import com.google.gson.Gson
import jp.umecchi.astarte4j.api.entities.status.Data
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
        /*
        val event = j.getString("event")
        val payload = j.getString("payload")
        if (event == "update") {
            val status = gson.fromJson(
                payload,
                Data::class.java
            )
            handler.onStatus(status)
        }
         */
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