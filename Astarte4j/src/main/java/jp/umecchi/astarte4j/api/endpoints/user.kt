package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.Parameter
import jp.umecchi.astarte4j.api.entities.status.Data
import jp.umecchi.astarte4j.api.entities.status.MediaStatus
import jp.umecchi.astarte4j.api.entities.status.Message
import jp.umecchi.astarte4j.api.entities.status.Status
import jp.umecchi.astarte4j.api.entities.user.AccessToken
import jp.umecchi.astarte4j.api.entities.user.Account
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class user(private val client: AstarteClient) {
    @Throws(AstarteRequestException::class)
    fun getToken(
        email: String, client_id: String, client_secret: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("grant_type", "password")
            append("email", email)
            append("client_id", client_id)
            append("client_secret", client_secret)
        }.build()
        return AstarteRequest(
            {
                client.post(
                    "user/get_token",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    )
                )
            },
            {
                client.getSerializer().fromJson(it, AccessToken::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getProfile(
        id: String?
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
                client.post(
                    "user/lock",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    )
                )
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
                client.post(
                    "user/unlock",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    )
                )
            },
            {
                client.getSerializer().fromJson(it, Message::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getPosts(
        request_data: String?,
        user_id: String?,
        oldest_id: String?,
        newest_id: String?
    ): AstarteRequest<Status> {
        return AstarteRequest(
            {
                val base_path = "user/posts"
                //val parameter = "?request_data=$request_data&user_id=$user_id&oldest_id=$oldest_id&newest_id=$newest_id"
                var parameter = ""
                if (request_data != null || user_id != null || oldest_id != null || newest_id != null) {
                    parameter += "?"
                }
                if (request_data != null) {
                    parameter += "request_data=$request_data"
                }
                if (user_id != null) {
                    if (request_data != null) {
                        parameter += "&"
                    }
                    parameter += "user_id=$user_id"
                }
                if (oldest_id != null) {
                    if (request_data != null || user_id != null) {
                        parameter += "&"
                    }
                    parameter += "oldest_id=$oldest_id"
                }
                if (newest_id != null) {
                    if (request_data != null || user_id != null) {
                        parameter += "&"
                    }
                    parameter += "newest_id=$newest_id"
                }
                client.get("$base_path$parameter")
            },
            {
                if (request_data.equals("media")) {
                    client.getSerializer().fromJson(it, MediaStatus::class.java)
                } else {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
            }
        )
    }
}
