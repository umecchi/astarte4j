package jp.umecchi.astarte4j

import android.util.Log
import com.google.gson.Gson
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import jp.umecchi.astarte4j.api.stream.AstarteWebSocketListener
import jp.umecchi.astarte4j.api.stream.Handler
import jp.umecchi.astarte4j.api.stream.StreamListener
import okhttp3.*
import java.io.IOException

open class AstarteClient
private constructor(
    private val instanceName: String,
    private val client: OkHttpClient,
    private val gson: Gson
) {

    class Builder(
        private val instanceName: String,
        private val okHttpClientBuilder: OkHttpClient.Builder,
        private val gson: Gson
    ) {

        private var accessToken: String? = null
        private var basicToken: String? = null
        private var debug = false

        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        fun basicToken(basicToken: String) = apply {
            this.basicToken = basicToken
        }

        fun debug() = apply {
            this.debug = true
        }

        fun build(): AstarteClient {
            return AstarteClient(
                instanceName,
                okHttpClientBuilder
                    .addNetworkInterceptor(AuthorizationInterceptor(accessToken, basicToken))
                    .build(),
                gson
            ).also {
                it.debug = debug
            }
        }
    }

    private var debug = false

    fun debugPrint(log: String) {
        if (debug) {
            println(log)
            Log.d("debug", log)
        }
    }

    private class AuthorizationInterceptor(
        val accessToken: String? = null,
        val basicToken: String? = null
    ) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val compressedRequest = originalRequest.newBuilder()
                .headers(originalRequest.headers)
                .method(originalRequest.method, originalRequest.body)
                .apply {
                    accessToken?.let {
                        header("Authorization", String.format("Bearer %s", it))
                    }
                }
                .apply {
                    basicToken?.let {
                        header("Authorization", String.format("Basic %s", it))
                    }
                }
                .build()
            return chain.proceed(compressedRequest)
        }
    }

    val baseUrl = "https://${instanceName}/api"

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    open fun get(path: String, parameter: Parameter? = null): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val urlWithParams = parameter?.let {
                "$url?${it.build()}"
            } ?: url
            val call = client.newCall(
                Request.Builder()
                    .url(urlWithParams)
                    .get()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun postUrl(url: String, body: RequestBody): Response {
        try {
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .post(body)
                    .build()
            )
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw AstarteRequestException(e)
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun post(path: String, body: RequestBody) =
        postUrl("$baseUrl/$path", body)

    open fun patch(path: String, body: RequestBody): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .patch(body)
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun put(path: String, body: RequestBody): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .put(body)
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun delete(path: String): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .delete()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun parametersDelete(path: String, body: RequestBody): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .delete(body)
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }

    open fun ws(handler: Handler, listener: StreamListener,url: String){
        try {
            debugPrint(url)
            val request = Request.Builder()
                .url(url)
                .build()
            val wsl = AstarteWebSocketListener(handler,listener)
            client.newWebSocket(request,wsl)
            return
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }
}
