package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.status.Data


class DeleteStatus(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val data: Data,
) {
}
