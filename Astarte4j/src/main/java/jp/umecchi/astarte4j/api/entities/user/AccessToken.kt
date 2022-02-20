package jp.umecchi.astarte4j.api.entities.user

import com.google.gson.annotations.SerializedName


class AccessToken(
        @SerializedName("access_token")
        val access_token: String = "",

        @SerializedName("token_type")
        val token_type: String = "") {
}
