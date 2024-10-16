package com.theek.swapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.theek.swapi.presentation.screen.AllPlanetsScreen
import com.theek.swapi.presentation.ui.theme.XitebTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XitebTheme {
                AllPlanetsScreen()
            }
        }
    }
}