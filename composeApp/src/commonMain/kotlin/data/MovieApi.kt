package data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MovieApi {
    private val accessToken: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1YzI4Yzk3MjM4N2M3OGU2ZDNkNzMyYjhhZWJhNmVhZiIsIm5iZiI6MTc3MzA2NTgyNC4wMzAwMDAyLCJzdWIiOiI2OWFlZDY2MDA5YjQ1NDk5MzFjMWNjZjIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.vfB5eTMFav89TfNa11R86orR6pMOAYAeNsI_AfA-omQ"
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend fun getTrendingMovies(): List<Movie> {
        val response: MovieResponse = httpClient.get("https://api.themoviedb.org/3/trending/movie/day") {
            header("Authorization", accessToken)
            parameter("language", "en-US")
        }.body()
        return  response.results
    }
}