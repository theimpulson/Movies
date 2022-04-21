package dev.theimpulson.movies.api.data

data class Show(
    val backdrop_path: String = String(),
    val first_air_date: String = String(),
    val genre_ids: List<Int> = emptyList(),
    val id: Int = 0,
    val name: String = String(),
    val origin_country: List<String> = emptyList(),
    val original_language: String = String(),
    val original_name: String = String(),
    val overview: String = String(),
    val popularity: Double = 0.0,
    val poster_path: String = String(),
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)