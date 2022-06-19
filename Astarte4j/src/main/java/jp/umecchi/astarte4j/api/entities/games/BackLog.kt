package jp.umecchi.astarte4j.api.entities.games

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account


class BackLog(
    @SerializedName("content")
    val content: String,

    @SerializedName("fake")
    val fake: String,

    @SerializedName("rarity")
    val rarity: String,
) {
}
