package jp.umecchi.astarte4j

import android.util.Log
import com.google.gson.Gson
import jp.umecchi.astarte4j.api.exception.AstarteRequestException
import jp.umecchi.astarte4j.api.stream.AstarteWebSocketListener
import jp.umecchi.astarte4j.api.stream.Handler
import jp.umecchi.astarte4j.api.stream.StreamListener
import okhttp3.*
import java.io.IOException

open class AstarteWsClient
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
        private var debug = false

        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        fun debug() = apply {
            this.debug = true
        }

        fun build(): AstarteWsClient {
            return AstarteWsClient(
                instanceName,
                okHttpClientBuilder
                    .addNetworkInterceptor(AuthorizationInterceptor(accessToken))
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
        val accessToken: String? = null
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
                .build()
            return chain.proceed(compressedRequest)
        }
    }

    val baseUrl = "wss://${instanceName}/ws"

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    open fun ws(handler: Handler, listener: StreamListener) : WebSocket{
        try {
            debugPrint(baseUrl)
            val request = Request.Builder()
                .url(baseUrl)
                .addHeader("Connection","Upgrade")
                .addHeader("Upgrade","websocket")
                .build()
            val wsl = AstarteWebSocketListener(handler, listener)
            return client.newWebSocket(request, wsl)
        } catch (e: IOException) {
            throw AstarteRequestException(e)
        }
    }
}
