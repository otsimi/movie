package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.adapters.MovieAdapter
import com.example.zozamax_app.databinding.FragmentTopBinding
import com.example.zozamax_app.repository.TopMovieRepo
import com.example.zozamax_app.viewmodel.TopModelProvider
import com.example.zozamax_app.viewmodel.TopViewModel
import com.skydoves.transformationlayout.onTransformationStartContainer

private const val TAG = "TopMovies"

class TopFragment : Fragment() {

    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTransformationStartContainer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val repo = TopMovieRepo()
        val productViewModel: TopViewModel by viewModels {
            TopModelProvider(repo)
        }

        productViewModel.topMovies.observe(viewLifecycleOwner) { movies ->
            Log.d(TAG, "Top movies: $movies")
            if (movies.isNotEmpty()) {
                binding.recView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = MovieAdapter(movies)
                    setHasFixedSize(true)
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
