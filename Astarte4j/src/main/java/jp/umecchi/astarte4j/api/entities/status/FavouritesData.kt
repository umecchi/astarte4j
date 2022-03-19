package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account
import java.util.*

class FavouritesData(
    @SerializedName("favourites_id")
    val favourites_id: String,

    @SerializedName("statusData")
    val status_data: Data,
) {
}