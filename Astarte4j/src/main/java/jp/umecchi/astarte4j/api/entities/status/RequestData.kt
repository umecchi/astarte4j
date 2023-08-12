package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import java.util.*

class RequestData(
    @SerializedName("request_id")
    val request_id: String,

    @SerializedName("user_id")
    val user_id: String,

    @SerializedName("request_user_id")
    val request_user_id: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("create_at")
    val create_at: Date,

    @SerializedName("update_at")
    val update_at: Date,
    ) {
}
