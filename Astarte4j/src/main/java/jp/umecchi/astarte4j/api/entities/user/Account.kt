package jp.umecchi.astarte4j.api.entities.user

import com.google.gson.annotations.SerializedName


class Account(
    @SerializedName("user_id")
    val user_id: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("display_name")
    val display_name: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("icon_url")
    val icon_url: String,

    @SerializedName("header_url")
    val header_url: String,

    @SerializedName("lock")
    val lock: Boolean,

    @SerializedName("aspoints")
    val aspoints: Int?,

    @SerializedName("secret_word")
    val secret_word: String?,

    @SerializedName("follow_limit")
    val follow_limit: String?,

    @SerializedName("follow_message")
    val follow_message: Boolean?,
) {
}
