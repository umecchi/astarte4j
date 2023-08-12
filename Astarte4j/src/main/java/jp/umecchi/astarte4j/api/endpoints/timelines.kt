package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.api.entities.status.Status
import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.exception.AstarteRequestException

class timelines(private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun getPublic(
        newest_id: String?,
        oldest_id: String?
    ): AstarteRequest<Status> {
        return AstarteRequest(
            {
                val base_path = "timelines/public"
                if (newest_id != null) {
                    client.get("$base_path?newest_id=$newest_id")
                } else if (oldest_id != null) {
                    client.get("$base_path?oldest_id=$oldest_id")
                } else {
                    client.get(base_path)
                }
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getHome(
        newest_id: String?,
        oldest_id: String?
    ): AstarteRequest<Status> {
        return AstarteRequest(
            {
                val base_path = "timelines/home"
                if (newest_id != null) {
                    client.get("$base_path?newest_id=$newest_id")
                } else if (oldest_id != null) {
                    client.get("$base_path?oldest_id=$oldest_id")
                } else {
                    client.get(base_path)
                }
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }
}
