package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.Movie
import ui.Components.MoviePosterCard
import ui.HomeScreenUIState

@Composable
fun TrendingGrid(state: HomeScreenUIState, onMovieClick: (Movie) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Netflix style 3-column grid
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.movies) { movie ->
                MoviePosterCard(movie = movie, onClick = { onMovieClick(movie) })
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Red // Netflix Red
            )
        }
    }
}