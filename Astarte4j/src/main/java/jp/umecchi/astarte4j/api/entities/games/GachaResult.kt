package jp.umecchi.astarte4j.api.entities.games

import com.google.gson.annotations.SerializedName


class GachaResult(
    @SerializedName("content_id")
    val content_id: String,

    @SerializedName("content_name")
    val content_name: String,

    @SerializedName("season")
    val season: String,

    @SerializedName("rarity")
    val rarity: String,

    @SerializedName("ownership")
    val ownership: String,
) {
}
