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
import jp.umecchi.astarte4j.api.entities.user.CreateAccount
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class user(private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun createAccount(
        email: String, username: String, password: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("username", username)
            append("password", password)
            append("email", email)
        }.build()
        return AstarteRequest(
            {
                client.post(
                    "user/create_account",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                    )
                )
            },
            {
                client.getSerializer().fromJson(it, CreateAccount::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun emailVerify(
        user_id: String, code: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("user_id", user_id)
            append("code", code)
        }.build()
        return AstarteRequest(
            {
                client.post(
                    "user/email_verify",
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
    fun passwordResetRequest(
        user_id: String, email: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("user_id", user_id)
            append("email", email)
        }.build()
        return AstarteRequest(
            {
                client.post(
                    "user/password_reset_request",
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
    fun passwordReset(
        user_id: String,
        email: String,
        code: String,
        new_password: String,
        retype_new_password: String
    ): AstarteRequest<AccessToken> {
        val parameters = Parameter().apply {
            append("user_id", user_id)
            append("email", email)
            append("code",code)
            append("new_password",new_password)
            append("retype_new_password",retype_new_password)
        }.build()
        return AstarteRequest(
            {
                client.post(
                    "user/password_reset",
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
                    client.get("$base_path?id=$id")
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
        user_id: String?,
        oldest_id: String?,
        newest_id: String?
    ): AstarteRequest<Status> {
        return AstarteRequest(
            {
                val base_path = "user/posts"
                val request_data = "status"
                //val parameter = "?request_data=$request_data&user_id=$user_id&oldest_id=$oldest_id&newest_id=$newest_id"
                var parameter = ""
                if (user_id != null || oldest_id != null || newest_id != null) {
                    parameter += "?"
                }
                parameter += "request_data=$request_data"
                if (user_id != null) {
                    parameter += "&"

                    parameter += "user_id=$user_id"
                }
                if (oldest_id != null) {
                    parameter += "&"
                    parameter += "oldest_id=$oldest_id"
                }
                if (newest_id != null) {
                    parameter += "&"
                    parameter += "newest_id=$newest_id"
                }
                client.get("$base_path$parameter")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getMedia(
        user_id: String?,
        oldest_id: String?,
        newest_id: String?
    ): AstarteRequest<MediaStatus> {
        return AstarteRequest(
            {
                val base_path = "user/posts"
                val request_data = "media"
                //val parameter = "?request_data=$request_data&user_id=$user_id&oldest_id=$oldest_id&newest_id=$newest_id"
                var parameter = ""
                if (user_id != null || oldest_id != null || newest_id != null) {
                    parameter += "?"
                }
                parameter += "request_data=$request_data"
                if (user_id != null) {
                    parameter += "&"

                    parameter += "user_id=$user_id"
                }
                if (oldest_id != null) {
                    parameter += "&"
                    parameter += "oldest_id=$oldest_id"
                }
                if (newest_id != null) {
                    parameter += "&"
                    parameter += "newest_id=$newest_id"
                }
                client.get("$base_path$parameter")
            },
            {
                client.getSerializer().fromJson(it, MediaStatus::class.java)
            }
        )
    }
}
