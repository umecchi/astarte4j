package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import java.util.*

class FavouritesData(
    @SerializedName("favourites_id")
    val favourites_id: String,

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