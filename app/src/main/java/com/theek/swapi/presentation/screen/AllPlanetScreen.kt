package com.theek.swapi.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theek.swapi.R
import com.theek.swapi.presentation.UiState
import com.theek.swapi.presentation.screen.components.DetailedPlanetPane
import com.theek.swapi.presentation.screen.components.PlanetList
import com.theek.swapi.presentation.viewmodel.AllPlanetViewModel

@Composable
fun AllPlanetsScreen(allPlanetViewModel: AllPlanetViewModel = hiltViewModel()) {

    val planetState by allPlanetViewModel.allPlanetsState.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = planetState) {
            is UiState.Success -> {
                if (allPlanetViewModel.shouldOpenPlanetDetailedPane.not()) {
                    PlanetList(
                        modifier = Modifier.fillMaxSize(),
                        planets = state.data,
                        onPlanetClick = allPlanetViewModel::onOpeningDetailedPlanetPane
                    )
                }
            }

            is UiState.Failure -> {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message ?: stringResource(R.string.oops_something_wrong),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
            }

            UiState.Loading -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.please_wait),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        }
    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = allPlanetViewModel.shouldOpenPlanetDetailedPane,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        allPlanetViewModel.selectedPlanet?.let {
            DetailedPlanetPane(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                planet = it,
                onBackPress = allPlanetViewModel::onHideDetailedPlanetPane
            )
        }
    }
}