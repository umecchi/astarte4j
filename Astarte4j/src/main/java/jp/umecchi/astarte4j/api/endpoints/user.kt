package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.Parameter
import jp.umecchi.astarte4j.api.entities.status.Message
import jp.umecchi.astarte4j.api.entities.user.AccessToken
import jp.umecchi.astarte4j.api.entities.user.Account
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class user(private val client: AstarteClient) {
    @Throws(AstarteRequestException::class)
    fun getToken(client_id: String, client_secret: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("grant_type","password")
            append("client_id",client_id)
            append("client_secret",client_secret)
        }.build()
        return AstarteRequest(
            {
                client.post("user/get_token",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    ))
            },
            {
                client.getSerializer().fromJson(it, AccessToken::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getProfile(
        id:String?
    ): AstarteRequest<Account> {
        return AstarteRequest(
            {
                val base_path = "user/profile"
                if (id != null) {
                    client.get("$base_path?id=id")
                } else {
                    client.get(base_path)
                }
            },
            {
                client.getSerializer().fromJson(it, Account::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun lock(): AstarteRequest<Message> {
        val parameters = Parameter().apply {
        }.build()
        return AstarteRequest(
            {
                client.post("user/lock",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    ))
            },
            {
                client.getSerializer().fromJson(it, Message::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun unlock(): AstarteRequest<Message> {
        val parameters = Parameter().apply {
        }.build()
        return AstarteRequest(
            {
                client.post("user/unlock",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    ))
            },
            {
                client.getSerializer().fromJson(it, Message::class.java)
            }
        )
    }
}
