package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import java.util.*

class ReblogStatusData(
    @SerializedName("post_id")
    val post_id: String,

    @SerializedName("account")
    val account: Account,

    @SerializedName("content")
    val content: String,

    @SerializedName("visibility")
    val visibility: String = Data.Visibility.Public.value,

    @SerializedName("reply_destination_id")
    val reply_destination_id: String,

    @SerializedName("reply")
    val reply: Boolean,

    @SerializedName("sensitive")
    val sensitive: Boolean,

    @SerializedName("introduction_sentence")
    val introduction_sentence: String,

    @SerializedName("favourited")
    val favourited: Boolean,

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
        //Home("home"),
        //Followers("followers"),
        //Specified("specified"),
        //Private("private")
    }
}