package jp.umecchi.astarte4j.api.entities.status

import com.google.gson.annotations.SerializedName
import jp.umecchi.astarte4j.api.entities.user.Account

class FollowRequestResponseData(
    @SerializedName("request_data")
    val request_data: RequestData,

    @SerializedName("profile")
    val profile: Account,
) {
}
