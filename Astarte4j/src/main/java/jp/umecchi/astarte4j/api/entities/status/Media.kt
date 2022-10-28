package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import java.util.*

class Media(
    @SerializedName("media_id")
    val media_id: String,

    @SerializedName("user_id")
    val user_id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("visibility")
    val visibility: String = Visibility.Public.value,

    @SerializedName("search")
    val search: Boolean,

    @SerializedName("public")
    val public: Boolean,

    @SerializedName("data_type")
    val data_type: String,

    @SerializedName("bytes")
    val bytes: Long,

    @SerializedName("create_at")
    val create_at: Date,

    @SerializedName("update_at")
    val update_at: Date,
) {
    enum class Visibility(val value: String) {
        Public("public"),
        Direct("direct")
    }
}