package jp.umecchi.astarte4j.api.entities.games

import com.google.gson.annotations.SerializedName
import java.util.*


class GachaBackLog(
    @SerializedName("backlog")
    val backlog: BackLog,

    @SerializedName("update_at")
    val update_at: Date,
) {
}
