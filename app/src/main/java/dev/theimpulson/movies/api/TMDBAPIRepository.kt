package dev.theimpulson.movies.api

import android.util.Log
import dev.theimpulson.movies.api.data.ShowsData
import javax.inject.Inject

class TMDBAPIRepository @Inject constructor(
    private val tmdbapiInterface: TMDBAPIInterface
) {

    private val TAG = TMDBAPIRepository::class.java.simpleName

    suspend fun getTopShows(page: Int): ShowsData {
        val result = tmdbapiInterface.getTopShows(page)
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!
        } else {
            Log.d(TAG, "Failed to get top shows, response code: ${result.code()}")
            ShowsData()
        }
    }

    suspend fun getSimilarShows(id: Int, page: Int): ShowsData {
        val result = tmdbapiInterface.getSimilarShows(id, page)
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!
        } else {
            Log.d(TAG, "Failed to get similar shows, response code: ${result.code()}")
            ShowsData()
        }
    }

}