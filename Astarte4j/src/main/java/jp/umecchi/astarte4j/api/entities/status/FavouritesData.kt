package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
class FavouritesData(
    @SerializedName("favourites_id")
    val favourites_id: String,

    @SerializedName("status_data")
    val status_data: Data,
) {
}