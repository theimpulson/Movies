package dev.theimpulson.movies.topshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.theimpulson.movies.R
import dev.theimpulson.movies.databinding.FragmentTopShowsBinding

@AndroidEntryPoint
class TopShowsFragment : Fragment(R.layout.fragment_top_shows) {

    private var _binding: FragmentTopShowsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopShowsBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}