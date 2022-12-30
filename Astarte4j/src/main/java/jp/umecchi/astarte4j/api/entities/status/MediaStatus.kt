package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName

class MediaStatus(
    @SerializedName("newest_id")
    val newest_id: String,

    @SerializedName("oldest_id")
    val oldest_id: String,

    @SerializedName("data")
    val data: kotlin.collections.List<Media>,
) {
}
