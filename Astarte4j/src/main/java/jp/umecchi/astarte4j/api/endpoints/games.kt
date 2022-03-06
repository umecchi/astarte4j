package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.api.entities.games.Gacha
import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.entities.games.GachaResult
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

    @Throws(AstarteRequestException::class)
    fun getGachaResult(): AstarteRequest<GachaResult> {
        return AstarteRequest(
            {
                client.get("games/gacha_result")
            },
            {
                client.getSerializer().fromJson(it, GachaResult::class.java)
            }
        )
    }
}
