package com.example.testapplication.shared

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

interface Api {
    suspend fun getData(): BreedResult
}

class ApiImpl : Api {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                ignoreUnknownKeys = true
                isLenient = false
                allowSpecialFloatingPointValues = false
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.NONE
        }
    }

    override suspend fun getData(): BreedResult {
        return client.get<BreedResult> {
            dogs("api/breeds/list/all")
        }
    }

    private fun HttpRequestBuilder.dogs(path: String) {
        url {
            takeFrom("https://dog.ceo/")
            encodedPath = path
        }
    }
}

@Serializable
data class BreedResult(
        val message: HashMap<String, List<String>>,
        var status: String
)