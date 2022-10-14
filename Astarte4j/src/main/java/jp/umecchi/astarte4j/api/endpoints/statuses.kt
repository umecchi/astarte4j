package jp.umecchi.astarte4j.api.endpoints

import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.Parameter
import jp.umecchi.astarte4j.api.entities.status.Data
import jp.umecchi.astarte4j.api.entities.status.DeleteStatus
import jp.umecchi.astarte4j.api.entities.status.Message
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class statuses(private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun new_status(
        content: String?,
        visibility: Data.Visibility = Data.Visibility.Public,
        reply_destination_id: String?,
        sensitive: Boolean?,
        introduction_sentence: String?
    ): AstarteRequest<Data> {
        val parameters = Parameter().apply {
            content?.let { append("content", it) }
            append("visibility",visibility.value)
            reply_destination_id?.let { append("reply_destination_id", it) }
            sensitive?.let { append("sensitive", it) }
            introduction_sentence?.let { append("introduction_sentence:", it) }
        }.build()
        return AstarteRequest(
            {
                client.post("statuses/new_status",
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        parameters!!
                ))
            },
            {
                client.getSerializer().fromJson(it, Data::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun delete_status(
        post_id: String?
    ): AstarteRequest<DeleteStatus> {
        return AstarteRequest(
            {
                client.delete("statuses/delete_status?post_id=$post_id")
            },
            {
                client.getSerializer().fromJson(it, DeleteStatus::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun favourite(
        post_id: String
    ): AstarteRequest<Message> {
        val parameters = Parameter().apply {
            append("post_id",post_id)
        }.build()
        return AstarteRequest(
            {
                client.post("statuses/favourite",
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
    fun unfavourite(
        post_id: String
    ): AstarteRequest<Message> {
        val parameters = Parameter().apply {
            append("post_id",post_id)
        }.build()
        return AstarteRequest(
            {
                client.post("statuses/unfavourite",
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
    fun reblog(
        post_id: String
    ): AstarteRequest<Message> {
        val parameters = Parameter().apply {
            append("post_id",post_id)
        }.build()
        return AstarteRequest(
            {
                client.post("statuses/reblog",
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
    fun unreblog(
        post_id: String
    ): AstarteRequest<Message> {
        val parameters = Parameter().apply {
            append("post_id",post_id)
        }.build()
        return AstarteRequest(
            {
                client.post("statuses/unreblog",
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
