package com.example.netflixclone

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.HomeEvent
import ui.HomeViewModel
import ui.components.TrendingGrid
import ui.screens.MovieDetailScreen


@Composable
fun App() {
    val viewModel: HomeViewModel = viewModel { HomeViewModel() }
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    MaterialTheme {
        // Crossfade handles the screen transition animation
        Crossfade(targetState = uiState.selectedMovie) { movie ->
            if (movie == null) {
                TrendingGrid(
                    state = uiState,
                    onMovieClick = { viewModel.onEvent(HomeEvent.SelectMovie(it)) }
                )
            } else {
                MovieDetailScreen(
                    movie = movie,
                    onBack = { viewModel.onEvent(HomeEvent.ClearSelection) }
                )
            }
        }
    }
}