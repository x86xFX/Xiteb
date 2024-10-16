package com.theek.swapi.data.remote

import com.theek.swapi.data.remote.dto.AllPlanetsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import javax.inject.Inject

class SwapiService @Inject constructor(private val client: HttpClient) {

    suspend fun getAllPlanets(): AllPlanetsDto {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "swapi.dev"
                path("api", "planets")
            }
        }.body<AllPlanetsDto>()
    }
}