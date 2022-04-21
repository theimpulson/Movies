package dev.theimpulson.movies.api.data

data class ShowsData(
    val page: Int = 0,
    val results: List<Show> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)