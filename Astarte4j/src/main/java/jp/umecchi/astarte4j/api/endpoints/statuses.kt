package jp.umecchi.astarte4j.api.endpoints

import android.util.Log
import jp.umecchi.astarte4j.AstarteClient
import jp.umecchi.astarte4j.AstarteRequest
import jp.umecchi.astarte4j.Parameter
import jp.umecchi.astarte4j.api.entities.status.Data
import jp.umecchi.astarte4j.api.entities.status.DeleteStatus
import jp.umecchi.astarte4j.api.entities.status.DetailStatus
import jp.umecchi.astarte4j.api.entities.status.Media
import jp.umecchi.astarte4j.api.entities.status.Message
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray

class statuses(private val client: AstarteClient) {

    @Throws(AstarteRequestException::class)
    fun new_status(
        content: String?,
        mediaIds: JSONArray?,
        visibility: Data.Visibility = Data.Visibility.Public,
        reply_destination_id: String?,
        reply_destination_user_ids: List<String>?,
        sensitive: Boolean?,
        introduction_sentence: String?
    ): AstarteRequest<Data> {
        val parameters = Parameter().apply {
            content?.let { append("content", it) }
            mediaIds?.let { append("media", it) }
            append("visibility",visibility.value)
            reply_destination_id?.let { append("reply_destination_id", it) }
            reply_destination_user_ids?.let { append("reply_destination_user_ids", it) }
            sensitive?.let { append("sensitive", it) }
            introduction_sentence?.let { append("introduction_sentence", it) }
        }.build()
        Log.d("debug", parameters.toString())
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

    @Throws(AstarteRequestException::class)
    fun post_media(
        file: MultipartBody.Part,
        visibility: Media.Visibility = Media.Visibility.Direct,
        search: Boolean?,
        public: Boolean?
    ): AstarteRequest<Media> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("visibility", visibility.toString())
            .addFormDataPart("search", search.toString())
            .addFormDataPart("public", public.toString())
            .addPart(file)
            .build()
        return AstarteRequest(
            {
                client.post("statuses/media",requestBody
                )
            },
            {
                client.getSerializer().fromJson(it, Media::class.java)
            }
        )
    }

    @Throws(AstarteRequestException::class)
    fun getStatuses(
        post_id: String?
    ): AstarteRequest<DetailStatus> {
        return AstarteRequest(
            {
                val base_path = "statuses"
                client.get("$base_path?post_id=$post_id")
            },
            {
                client.getSerializer().fromJson(it, DetailStatus::class.java)
            }
        )
    }
}
