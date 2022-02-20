package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.api.entities.games.Gacha
import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.exception.AstarteRequestException

class games(private val client: AstarteClient) {
    @Throws(AstarteRequestException::class)
    fun gacha(): AstarteRequest<Gacha> {
        return AstarteRequest(
            {
                client.get("games/gacha")
            },
            {
                client.getSerializer().fromJson(it, Gacha::class.java)
            }
        )
    }
}
