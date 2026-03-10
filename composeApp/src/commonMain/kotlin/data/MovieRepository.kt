package data

class MovieRepository(private val api: MovieApi) {
    suspend fun getTrendingMovies(): List<Movie> {
        return api.getTrendingMovies()
    }
}