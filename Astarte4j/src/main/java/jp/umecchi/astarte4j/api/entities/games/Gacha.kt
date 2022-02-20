package jp.umecchi.astarte4j.api.entities.games

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account


class Gacha(
    @SerializedName("accounts")
    val accounts: Account,

    @SerializedName("last_date")
    val last_date: String,

    @SerializedName("add_aspoint")
    val add_aspoint: Long,

    @SerializedName("result")
    val result: Result,
) {
}
