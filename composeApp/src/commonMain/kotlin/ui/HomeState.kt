package ui

import data.Movie

sealed interface HomeState {
    data object Loading : HomeState
    data class Success(val movies: List<Movie>) : HomeState
    data class Error(val message: String) : HomeState
}