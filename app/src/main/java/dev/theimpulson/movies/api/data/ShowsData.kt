package dev.theimpulson.movies.api.data

data class ShowsData(
    val page: Int = 0,
    val results: MutableList<Show> = mutableListOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)