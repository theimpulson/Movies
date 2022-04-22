package dev.theimpulson.movies.similarshows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import dev.theimpulson.movies.api.TMDBAPIInterface
import dev.theimpulson.movies.api.data.Show
import dev.theimpulson.movies.common.ShowDiffUtil
import dev.theimpulson.movies.databinding.RecyclerViewSimilarShowsBinding

class SimilarShowsRVAdapter :
    PagingDataAdapter<Show, SimilarShowsRVAdapter.ViewHolder>(ShowDiffUtil()) {

    inner class ViewHolder(val binding: RecyclerViewSimilarShowsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val show = getItem(position) ?: Show()

        holder.binding.apply {
            if (!show.backdrop_path.isNullOrBlank()) {
                backdropIV.load(TMDBAPIInterface.ASSETS_URL + show.backdrop_path) {
                    transformations(BlurTransformation(context))
                }
            }
            showPosterIV.load(TMDBAPIInterface.ASSETS_URL + show.poster_path)
            showTitleTV.text = show.name
            showDescTV.text = show.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewSimilarShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
