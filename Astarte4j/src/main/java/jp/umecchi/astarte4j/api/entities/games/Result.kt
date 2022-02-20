package jp.umecchi.astarte4j.api.entities.games

import com.google.gson.annotations.SerializedName


class Result(
    @SerializedName("rarity")
    val rarity: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("fake")
    val fake: String,
) {
}
