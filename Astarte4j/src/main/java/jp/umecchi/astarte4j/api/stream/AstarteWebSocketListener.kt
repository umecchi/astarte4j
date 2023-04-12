package jp.umecchi.astarte4j.api.stream

import okhttp3.*
import okio.ByteString

class AstarteWebSocketListener(
    val handler: Handler,
    val listener: StreamListener
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
        println("Received binary message: ${bytes.hex()}")
        handler.onMessage(bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("Connection closed: $code $reason")
        listener.onDisconnect()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("Connection failed: ${t.localizedMessage}")
    }
}