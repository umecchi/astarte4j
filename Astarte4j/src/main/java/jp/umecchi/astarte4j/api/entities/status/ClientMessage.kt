package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName


class ClientMessage(
    @SerializedName("id")
    val id: String,

    @SerializedName("from")
    val from: String,

    @SerializedName("data_type")
    val data_type: String = dataType.Text.value,

    @SerializedName("data")
    val data: String
) {

    enum class dataType(val value: String) {
        Text("Text"),
        StatusData("StatusData"),
        NotificationStatus("NotificationStatus")
    }
}
