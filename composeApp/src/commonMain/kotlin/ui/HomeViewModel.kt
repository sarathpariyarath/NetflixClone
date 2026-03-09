package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.MovieApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val api: MovieApi = MovieApi()

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

init {

}
    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val movieList = api.getTrendingMovies()
                _state.value = HomeState.Success(movieList)
            } catch (e: Exception) {
                _state.value = HomeState.Error(message = "Failed to load ${e.message})")
            }
        }
    }

}