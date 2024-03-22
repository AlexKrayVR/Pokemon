package com.alex.pokemon.data

import android.annotation.SuppressLint
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.request
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientProvider {
    private val cookiesStorage = AcceptAllCookiesStorage()
    private val clients = mutableMapOf<HttpClientProvider.LogLevel, HttpClient>()

    internal var isLoggingEnabled = true

    private fun httpClient(
        level: LogLevel,
        config: (HttpClientConfig<*>.() -> Unit)? = null,
    ): HttpClient {
        log("create http, logging enabled - ${isLoggingEnabled}")
        return httpEngine {
            expectSuccess = true

            install(ContentNegotiation) {
                json(this@HttpClientProvider.json)
            }

            install(ResponseObserver) {
                onResponse { response ->
                    log("Request time: ${response.request.url} ${response.status.value}>>${response.responseTime.timestamp - response.requestTime.timestamp}ms")
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 20000
                connectTimeoutMillis = 15000
            }

            install(HttpCookies) {
                storage = cookiesStorage
            }

            if (isLoggingEnabled) {
                install(Logging) {
                    logger = this@HttpClientProvider.logger
                    this.level = when (level) {
                        LogLevel.Full -> io.ktor.client.plugins.logging.LogLevel.ALL
                        LogLevel.Body -> io.ktor.client.plugins.logging.LogLevel.BODY
                        LogLevel.Small -> io.ktor.client.plugins.logging.LogLevel.INFO
                    }
                }
            }

            //install(SystemInfoFeature)
//            install(DeviceMetricsFeature) {
//                metrics = DeviceMetrics.deviceMetrics
//            }
//            install(UserAgent) {
//                _userAgent.value?.let {
//                    agent = it
//                }
//            }
            config?.invoke(this)
        }
    }

    suspend fun httpGet(
        baseUrl: String,
        urlSuffix: String,
        body: Any? = null,
        queries: List<Pair<String, String>>? = null,
        logLevel: LogLevel = LogLevel.Body,
    ): String = commonClient(logLevel).get(baseUrl + urlSuffix) {
        if (body != null) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        queries?.forEach { pair -> parameter(pair.first, pair.second) }
    }.bodyAsText()

    private fun commonClient(
        log: LogLevel = LogLevel.Body,
        needInfiniteRequestTimeout: Boolean = false,
    ) = clients.getOrPut(log) {
        httpClient(log) {
            defaultRequest {
//                AuthPreferencesStorage.instance.headers().forEach { (key, value) ->
//                    // Authorization header is managed by KTOR plugin
//                    // It seems that iOS uses whole headers set so we shouldn't remove it
//                    // Until iOS fully migrate to KMM and KTOR
//                    if (key != "Authorization")
//                        header(key, value)
//                }
            }
        }
    }.config {
        // set config for each client to prevent caching of wrong tokens
//        install(Auth) {
//            bearer {
//                loadTokens { loadTokens() }
//                refreshTokens { refreshTokens() }
//            }
//        }
        if (needInfiniteRequestTimeout) {
            install(HttpTimeout) {
                requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            }
        }
    }


    private val logger = object : Logger {
        override fun log(message: String) {
            Log.d("KTOR", message)
        }
    }

    private fun log(message: String) = Log.d("HTTPCLIENT_KTOR", message)
    enum class LogLevel { Small, Body, Full }


    val json
        get() = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
}

fun httpEngine(block: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(CIO) {
    engine {
        endpoint {
            maxConnectionsCount = MAX_CONNECTION_COUNTS
            maxConnectionsPerRoute = 1
            pipelineMaxSize = 1
        }
        https {
            trustManager = @SuppressLint("CustomX509TrustManager") object : X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit
                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit
                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
            }
        }
    }
    block.invoke(this)
}

private const val MAX_CONNECTION_COUNTS = 5
