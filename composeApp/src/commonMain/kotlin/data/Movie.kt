package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    val overview: String,
    @SerialName("vote_average") val rating: Double
) {
    val fullPosterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}

@Serializable
data class MovieResponse(
    val results: List<Movie> = emptyList()
)