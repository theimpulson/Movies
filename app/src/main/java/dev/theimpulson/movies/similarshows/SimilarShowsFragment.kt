package dev.theimpulson.movies.similarshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import dev.theimpulson.movies.R
import dev.theimpulson.movies.databinding.FragmentSimilarShowsBinding
import dev.theimpulson.movies.similarshows.adapter.SimilarShowsRVAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimilarShowsFragment : Fragment(R.layout.fragment_similar_shows) {

    private var _binding: FragmentSimilarShowsBinding? = null
    private val binding get() = _binding!!

    private val args: SimilarShowsFragmentArgs by navArgs()
    private val viewModel: SimilarShowsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSimilarShowsBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val similarShowsRVAdapter = SimilarShowsRVAdapter()
        val recyclerView = binding.similarShowsRV
        val pageSnapHelper = PagerSnapHelper()
        recyclerView.apply {
            adapter = similarShowsRVAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        pageSnapHelper.attachToRecyclerView(recyclerView)

        // load the shows
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSimilarShows(args.currentShow).distinctUntilChanged().collectLatest {
                similarShowsRVAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}