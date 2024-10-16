package com.theek.swapi.domain.repository

import com.theek.swapi.domain.model.AllPlanets
import com.theek.swapi.util.Response
import kotlinx.coroutines.flow.Flow

interface AllPlanetsRepository {
    fun getAllPlanets(): Flow<Response<AllPlanets>>
}