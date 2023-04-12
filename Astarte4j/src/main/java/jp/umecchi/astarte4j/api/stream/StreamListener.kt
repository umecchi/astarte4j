package jp.umecchi.astarte4j.api.stream

interface StreamListener {
    fun onConnect() {}
    fun onDisconnect() {}
}