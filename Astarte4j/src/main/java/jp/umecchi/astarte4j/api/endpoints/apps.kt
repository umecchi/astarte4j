package jp.umecchi.astarte4j.api.endpoints

import android.util.Log
import jp.umecchi.astarte4j.api.entities.apps.AppRegistration
import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.Parameter
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class apps(private val client: AstarteClient) {
    @Throws(AstarteRequestException::class)
    fun createApplication(
        name: String,
        website: String,
        scope_read: Boolean?,
        scope_write: Boolean?,
        scope_follow: Boolean?,
        scope_push: Boolean?,
        scope_games: Boolean?
    ): AstarteRequest<AppRegistration> {
        val parameters = Parameter().apply {
            append("name", name)
            append("website", website)
            scope_read?.let { append("scope_read", it) }
            scope_write?.let { append("scope_write", it) }
            scope_follow?.let { append("scope_follow", it) }
            scope_push?.let { append("scope_push", it) }
            scope_games?.let { append("scope_games", it) }
        }.build()
        Log.d("parameters", parameters.toString())
        return AstarteRequest(
            {
                client.post("apps/create_application",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    ))
            },
            {
                client.getSerializer().fromJson(it, AppRegistration::class.java)
            }
        )
    }
}
