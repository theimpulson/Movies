package dev.theimpulson.movies.topshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.theimpulson.movies.R
import dev.theimpulson.movies.databinding.FragmentTopShowsBinding
import dev.theimpulson.movies.topshows.adapter.TopShowsRVAdapter
import dev.theimpulson.movies.utils.CountingIdlingResourceSingleton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopShowsFragment : Fragment(R.layout.fragment_top_shows) {

    private var _binding: FragmentTopShowsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TopShowsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopShowsBinding.bind(view)

        val topShowsRVAdapter = TopShowsRVAdapter()
        binding.topShowsRV.apply {
            adapter = topShowsRVAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        // load the shows
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTopShows().distinctUntilChanged().collectLatest {
                CountingIdlingResourceSingleton.decrement()
                topShowsRVAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
