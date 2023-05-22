package jp.umecchi.astarte4j.api.stream

import jp.umecchi.astarte4j.api.entities.status.ClientMessage

interface Handler {

    fun onMessage(clientMessage: ClientMessage)
}