package dev.theimpulson.movies.topshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.theimpulson.movies.R
import dev.theimpulson.movies.databinding.FragmentTopShowsBinding

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