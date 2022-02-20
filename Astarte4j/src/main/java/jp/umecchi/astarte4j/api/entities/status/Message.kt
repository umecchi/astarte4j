package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName

class Message(
    @SerializedName("message")
    val message: String,
) {
}
