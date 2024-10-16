package com.theek.swapi.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theek.swapi.domain.model.AllPlanets
import com.theek.swapi.domain.repository.AllPlanetsRepository
import com.theek.swapi.presentation.UiState
import com.theek.swapi.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AllPlanetViewModel @Inject constructor(allPlanetsRepository: AllPlanetsRepository) : ViewModel() {

    val allPlanetsState: StateFlow<UiState<AllPlanets>> = allPlanetsRepository.getAllPlanets()
        .map {
            when (it) {
                is Response.Failure -> {
                    UiState.Failure(it.message)
                }
                is Response.Success -> {
                    UiState.Success(it.data)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading
        )

    var shouldOpenPlanetDetailedPane by mutableStateOf(false)
        private set
    var selectedPlanet by mutableStateOf<AllPlanets.Planet?>(null)
        private set

    fun onOpeningDetailedPlanetPane(planet: AllPlanets.Planet) {
        selectedPlanet = planet
        shouldOpenPlanetDetailedPane = true
    }

    fun onHideDetailedPlanetPane() {
        shouldOpenPlanetDetailedPane = false
        selectedPlanet = null
    }
}