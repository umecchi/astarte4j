package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import java.util.*

class Data(
    @SerializedName("post_id")
    val post_id: String,

    @SerializedName("account")
    val account: Account,

    @SerializedName("content")
    val content: String,

    @SerializedName("visibility")
    val visibility: String = Visibility.Public.value,

    @SerializedName("reply")
    val reply: Boolean,

    @SerializedName("reply_destination_id")
    val reply_destination_id: String,

    @SerializedName("reply_destination_user_ids")
    val reply_destination_user_ids: List<String> = emptyList(),

    @SerializedName("reply_destination_status_data")
    val reply_destination_status_data: Data,

    @SerializedName("sensitive")
    val sensitive: Boolean,

    @SerializedName("introduction_sentence")
    val introduction_sentence: String,

    @SerializedName("favourited")
    val favourited: Boolean,

    @SerializedName("reblog")
    val reblog: Boolean,

    @SerializedName("reblog_content")
    val reblog_content: ReblogStatusData,

    @SerializedName("media")
    val media: List<Media> = emptyList(),

    @SerializedName("client_data")
    val client_data: ClientData,

    @SerializedName("create_at")
    val create_at: Date,

    @SerializedName("update_at")
    val update_at: Date,
) {
    enum class Visibility(val value: String) {
        Public("public"),
        Follower("follower")
    }
}
