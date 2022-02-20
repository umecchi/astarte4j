package jp.umecchi.astarte4j

import com.google.gson.JsonParser
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import okhttp3.Response
import java.lang.Exception

open class AstarteRequest<T>(
    private val executor: () -> Response,
    private val mapper: (String) -> Any
) {
    interface Action1<T> {
        fun invoke(arg: T)
    }

    private var action: (String) -> Unit = {}

    @JvmSynthetic
    fun doOnJson(action: (String) -> Unit) = apply {
        this.action = action
    }

    fun doOnJson(action: Action1<String>) = apply {
        this.action = { action.invoke(it) }
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(AstarteRequestException::class)
    fun execute(): T {
        val response = executor()
        if (response.isSuccessful) {
            try {
                val body = response.body!!.string()
                val element = JsonParser().parse(body)
                if (element.isJsonObject) {
                    action(body)
                    return mapper(body) as T
                } else {
                    val list = arrayListOf<Any>()
                    element.asJsonArray.forEach {
                        val json = it.toString()
                        action(json)
                        list.add(mapper(json))
                    }
                    return list as T
                }
            } catch (e: Exception) {
                throw AstarteRequestException(e)
            }
        } else {
            throw AstarteRequestException(response)
        }
    }
}