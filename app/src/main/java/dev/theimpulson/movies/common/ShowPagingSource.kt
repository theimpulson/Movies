package dev.theimpulson.movies.common

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.theimpulson.movies.api.TMDBAPIRepository
import dev.theimpulson.movies.api.data.Show
import dev.theimpulson.movies.utils.ShowType

class ShowPagingSource @AssistedInject constructor(
    @Assisted private val showType: ShowType,
    @Assisted private val show: Show,
    private val tmdbapiRepository: TMDBAPIRepository
) : PagingSource<Int, Show>() {

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val page = params.key ?: 1
        val response = if (showType == ShowType.TOP) {
            tmdbapiRepository.getTopShows(page)
        } else {
            // Insert current show on top
            val apiResponse = tmdbapiRepository.getSimilarShows(show.id, page)
            apiResponse.results.add(0, show)
            // Filter by id to ensure current show is not already present in response
            apiResponse.results.distinctBy { it.id }
            apiResponse
        }
        return LoadResult.Page(
            response.results,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (response.results.isEmpty()) null else page + 1
        )
    }

}