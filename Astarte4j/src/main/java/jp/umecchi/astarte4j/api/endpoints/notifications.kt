package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.entities.status.NotificationStatus
import jp.umecchi.astarte4j.api.exception.AstarteRequestException

class notifications (private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun getNotifications(
        newest_id: String?,
        oldest_id: String?
    ): AstarteRequest<NotificationStatus> {
        return AstarteRequest(
            {
                val base_path = "notifications"
                if (newest_id != null) {
                    client.get("$base_path?newest_id=$newest_id")
                } else if (oldest_id != null) {
                    client.get("$base_path?oldest_id=$oldest_id")
                } else {
                    client.get(base_path)
                }
            },
            {
                client.getSerializer().fromJson(it, NotificationStatus::class.java)
            }
        )
    }
}
