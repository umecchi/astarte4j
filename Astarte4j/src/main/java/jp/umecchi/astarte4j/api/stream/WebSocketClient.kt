package jp.umecchi.astarte4j.api.stream

import okhttp3.*
import okio.ByteString

class WebSocketClient(
    val handler: Handler,
    val listener: StreamListener,
    val url: String
) : WebSocketListener() {
    private val ws: WebSocket

    init {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        ws = client.newWebSocket(request, this)
    }

    fun send(message: String) {
        ws.send(message)
    }

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