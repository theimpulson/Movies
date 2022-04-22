package dev.theimpulson.movies.topshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.theimpulson.movies.api.data.Show
import dev.theimpulson.movies.common.ShowPSFactory
import dev.theimpulson.movies.utils.ShowType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopShowsViewModel @Inject constructor(
    private val showPSFactory: ShowPSFactory
) : ViewModel() {

    fun getTopShows(): Flow<PagingData<Show>> {
        val pagingConfig = PagingConfig(pageSize = 1, enablePlaceholders = false)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { showPSFactory.createShowPagingSource(ShowType.TOP) }
        )
            .flow
            .cachedIn(viewModelScope)
    }
}
