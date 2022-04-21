package dev.theimpulson.movies.common

import androidx.recyclerview.widget.DiffUtil
import dev.theimpulson.movies.api.data.Show

class ShowDiffUtil : DiffUtil.ItemCallback<Show>() {
    override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
        return when {
            oldItem.id != newItem.id -> false
            oldItem.name != newItem.name -> false
            oldItem.backdrop_path != newItem.backdrop_path -> false
            oldItem.first_air_date != newItem.first_air_date -> false
            oldItem.genre_ids != newItem.genre_ids -> false
            oldItem.origin_country != newItem.origin_country -> false
            oldItem.original_language != newItem.original_language -> false
            oldItem.original_name != newItem.original_name -> false
            oldItem.overview != newItem.overview -> false
            oldItem.popularity != newItem.popularity -> false
            oldItem.poster_path != newItem.poster_path -> false
            oldItem.vote_average != newItem.vote_average -> false
            oldItem.vote_count != newItem.vote_count -> false
            else -> true
        }
    }
}