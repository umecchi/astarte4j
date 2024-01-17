package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName

class DetailStatus(
    @SerializedName("detail_status")
    val detail_status: Data,

    @SerializedName("reply_tree")
    val dreply_tree: String,
) {
}
