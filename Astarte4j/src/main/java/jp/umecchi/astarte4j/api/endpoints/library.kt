package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.api.entities.status.FavouritesStatus
import jp.umecchi.astarte4j.api.exception.AstarteRequestException

class library (private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun getFavourites(
        newest_id: String?,
        oldest_id: String?
    ): AstarteRequest<FavouritesStatus> {
        return AstarteRequest(
            {
                val base_path = "library/favourites"
                if (newest_id != null) {
                    client.get("$base_path?newest_id=$newest_id")
                } else if (oldest_id != null) {
                    client.get("$base_path?oldest_id=$oldest_id")
                } else {
                    client.get(base_path)
                }
            },
            {
                client.getSerializer().fromJson(it, FavouritesStatus::class.java)
            }
        )
    }
}
