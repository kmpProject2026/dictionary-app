package ru.kpfa.dictionary.datalayer.sources.webservices

import ru.kpfa.dictionary.viewmodel.debugLogger
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json

class ApiClient {

    val baseUrl = "https://baroncelli.eu"

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
//        install(Logging) {
//            logger = Logger.DEFAULT
//            level = LogLevel.INFO
//        }
    }


    suspend inline fun <reified T:Any> getResponse(endpoint : String): T? {
        val url = baseUrl+endpoint
        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val resp = client.get(url).body<T>()
            debugLogger.log("$url API SUCCESS")
            return resp
        } catch (e: Exception) {
            debugLogger.log("$url API FAILED: "+e.message )
        }
        return null
    }


}
