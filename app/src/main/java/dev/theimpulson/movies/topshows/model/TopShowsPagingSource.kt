package dev.theimpulson.movies.topshows.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.theimpulson.movies.api.TMDBAPIRepository
import dev.theimpulson.movies.api.data.Show
import javax.inject.Inject

class TopShowsPagingSource @Inject constructor(
    private val tmdbapiRepository: TMDBAPIRepository
) : PagingSource<Int, Show>() {

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val page = params.key ?: 1
        val response = tmdbapiRepository.getTopShows(page)
        return LoadResult.Page(
            response.results,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (response.results.isEmpty()) null else page + 1
        )
    }

}