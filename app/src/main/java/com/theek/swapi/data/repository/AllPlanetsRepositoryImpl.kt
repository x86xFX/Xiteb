package com.theek.swapi.data.repository

import com.theek.swapi.data.mappers.toAllPlanets
import com.theek.swapi.data.remote.SwapiService
import com.theek.swapi.domain.model.AllPlanets
import com.theek.swapi.domain.repository.AllPlanetsRepository
import com.theek.swapi.util.Response
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

class AllPlanetsRepositoryImpl @Inject constructor(private val swapiService: SwapiService) : AllPlanetsRepository {

    override fun getAllPlanets(): Flow<Response<AllPlanets>> = flow {
        try {
            val planetsResponse = swapiService.getAllPlanets()
            emit(Response.Success(planetsResponse.toAllPlanets()))

        } catch (_: ClientRequestException) {
            emit(Response.Failure("Invalid Request"))

        } catch (_: UnknownHostException) {
            emit(Response.Failure("No internet connection"))

        } catch (_: Exception) {
            emit(Response.Failure("Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}