package dev.theimpulson.movies.topshows.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.theimpulson.movies.api.TMDBAPIInterface
import dev.theimpulson.movies.api.data.Show
import dev.theimpulson.movies.databinding.RecyclerViewTopShowsBinding

class TopShowsRVAdapter :
    PagingDataAdapter<Show, TopShowsRVAdapter.ViewHolder>(TopShowsDiffUtil()) {

    inner class ViewHolder(val binding: RecyclerViewTopShowsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = getItem(position) ?: Show()

        holder.binding.apply {
            showPosterIV.load(TMDBAPIInterface.ASSETS_URL + show.poster_path)
            showTitleTV.text = show.name
            showDescTV.text = show.overview
            showAIRTV.text = show.first_air_date
            showVotesAvgTV.text = show.vote_average.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewTopShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}