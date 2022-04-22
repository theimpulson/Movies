package dev.theimpulson.movies.common

import dagger.assisted.AssistedFactory
import dev.theimpulson.movies.api.data.Show
import dev.theimpulson.movies.utils.ShowType

@AssistedFactory
interface ShowPSFactory {

    fun createShowPagingSource(showType: ShowType, show: Show = Show()): ShowPagingSource
}
