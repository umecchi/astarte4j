package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account

class ClientData(
    @SerializedName("client_name")
    val client_name: String,

    @SerializedName("client_id")
    val client_id: String,

    @SerializedName("owner_account")
    val owner_account: Account,

    @SerializedName("website")
    val website: String,
    ) {
}
