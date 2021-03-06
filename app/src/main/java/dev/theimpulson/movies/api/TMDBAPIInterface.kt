package dev.theimpulson.movies.api

import dev.theimpulson.movies.api.data.ShowsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBAPIInterface {

    companion object {
        const val ASSETS_URL = "https://image.tmdb.org/t/p/original"
    }

    @GET("tv/top_rated")
    suspend fun getTopShows(
        @Query("page") page: Int = 1
    ): Response<ShowsData>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarShows(
        @Path("tv_id") tv_id: Int,
        @Query("page") page: Int = 1
    ): Response<ShowsData>
}
