package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import jp.umecchi.astarte4j.api.entities.status.Data
import java.util.*


class NotificationData(
    @SerializedName("notification_id")
    val notification_id: String,

    @SerializedName("activity_type")
    val activity_type: String,

    @SerializedName("account")
    val account: Account,

    @SerializedName("destination_status")
    val destination_status: Data,

    @SerializedName("reply_status")
    val reply_status: Data,

    @SerializedName("create_at")
    val create_at: Date,

    @SerializedName("update_at")
    val update_at: Date,
) {
}
