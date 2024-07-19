package jp.umecchi.astarte4j.api.entities.health

import com.google.gson.annotations.SerializedName


class Health(
        @SerializedName("database")
        val database: String = "",

        @SerializedName("websocket")
        val websocket: String = "",

        @SerializedName("endpoints")
        val endpoints: String = "") {
}
