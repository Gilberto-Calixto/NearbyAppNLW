package com.filnm.nearby.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorHttpClient {

    private const val NETWORK_TIMEOUT = 5_000L

    val httpClientAndroid by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json{
                        prettyPrint = true // Formatação do JSON
                        isLenient = true // Aceita campos extras
                        useAlternativeNames = true // Usa nomes alternativos
                        ignoreUnknownKeys = true // Ignora chaves desconhecidas
                        explicitNulls = true // Permite nulos explicitamente
                        useArrayPolymorphism = true // Permite polimorfismo de arrays
                        encodeDefaults = false // Não codifica campos sem valor
                    }
                )
            }

            install(HttpTimeout) { // Configuração do timeout
                requestTimeoutMillis =  NETWORK_TIMEOUT
                connectTimeoutMillis =  NETWORK_TIMEOUT
                socketTimeoutMillis = NETWORK_TIMEOUT
            }

            install(Logging) { // Configuração do logging
                level = LogLevel.ALL
            }
        }
    }
}