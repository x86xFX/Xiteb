package com.theek.swapi.data.mappers

import com.theek.swapi.data.remote.dto.AllPlanetsDto
import com.theek.swapi.domain.model.AllPlanets

fun AllPlanetsDto.toAllPlanets(): AllPlanets {
    return AllPlanets(
        count = count,
        next = next,
        previous = previous,
        results = results.map {
            AllPlanets.Planet(
                name = it.name,
                rotationPeriod = it.rotationPeriod,
                orbitalPeriod = it.orbitalPeriod,
                diameter = it.diameter,
                climate = it.climate,
                gravity = it.gravity,
                terrain = it.terrain,
                surfaceWater = it.surfaceWater,
                population = it.population,
                residents = it.residents,
                films = it.films,
                created = it.created,
                edited = it.edited,
                url = it.url
            )
        }
    )
}