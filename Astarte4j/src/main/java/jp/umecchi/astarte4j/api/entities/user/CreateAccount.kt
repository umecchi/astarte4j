package jp.umecchi.astarte4j.api.entities.user

import com.google.gson.annotations.SerializedName


class CreateAccount(
        @SerializedName("username")
        val username: String = "",

        @SerializedName("message")
        val message: String = "") {
}
