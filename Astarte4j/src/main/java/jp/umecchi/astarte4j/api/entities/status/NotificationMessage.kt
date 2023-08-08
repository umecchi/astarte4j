package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import jp.umecchi.astarte4j.api.entities.status.Data
import java.util.*


class NotificationMessage(
    @SerializedName("notification_id")
    val notification_id: String,

    @SerializedName("activity_type")
    val activity_type: String,

    @SerializedName("destination_user_id")
    val destination_user_id: String,

    @SerializedName("user_id")
    val user_id: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("create_at")
    val create_at: Date,

    @SerializedName("update_at")
    val update_at: Date,
) {
}
