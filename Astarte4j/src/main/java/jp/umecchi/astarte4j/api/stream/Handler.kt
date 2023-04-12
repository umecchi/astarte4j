package jp.umecchi.astarte4j.api.stream

interface Handler {

    fun onMessage(string: String)
}