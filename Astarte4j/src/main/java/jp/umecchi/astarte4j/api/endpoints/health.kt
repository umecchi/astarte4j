package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.entities.health.Health
import jp.umecchi.astarte4j.api.exception.AstarteRequestException

class health(private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun getHealth(
    ): AstarteRequest<Health> {
        return AstarteRequest(
            {
                val base_path = "health"
                client.get(base_path)
            },
            {
                client.getSerializer().fromJson(it, Health::class.java)
            }
        )
    }
}
