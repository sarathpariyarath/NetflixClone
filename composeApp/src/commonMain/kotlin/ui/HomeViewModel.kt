package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Movie
import data.MovieApi
import data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: MovieRepository = MovieRepository(MovieApi())
) : ViewModel() {
    private val api: MovieApi = MovieApi()

    private val _state = MutableStateFlow(HomeScreenUIState(isLoading = true))
    val state: StateFlow<HomeScreenUIState> = _state

    init {
        fetchTrendingMovies()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectMovie -> {
                _state.update { it.copy(selectedMovie = event.movie) }
            }

            HomeEvent.ClearSelection -> {
                _state.update { it.copy(selectedMovie = null) }
            }

            HomeEvent.Refresh -> fetchTrendingMovies()
        }
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val movieList = api.getTrendingMovies()
                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = movieList,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = emptyList(),
                        error = e.message
                    )
                }
            }
        }
    }

}

data class HomeScreenUIState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val selectedMovie: Movie? = null
)

sealed interface HomeEvent {
    data class SelectMovie(val movie: Movie) : HomeEvent
    data object ClearSelection : HomeEvent
    data object Refresh : HomeEvent
}