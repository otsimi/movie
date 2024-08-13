package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.adapters.MovieAdapter
import com.example.zozamax_app.databinding.FragmentNowplayingBinding
import com.example.zozamax_app.repository.NowPlayingMovieRepo
import com.example.zozamax_app.viewmodel.NowPlayingModelProvider
import com.example.zozamax_app.viewmodel.NowPlayingViewModel

class NowPlayingFragment : Fragment() {

    private lateinit var binding: FragmentNowplayingBinding
    private val TAG = "NowPlayingFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNowplayingBinding.inflate(inflater, container, false)
        val repo = NowPlayingMovieRepo()
        val productViewModel: NowPlayingViewModel by viewModels {
            NowPlayingModelProvider(repo)
        }

        productViewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer { movies ->

            Log.d(TAG, "nowplaying movies -> $movies")
            if (movies.isNotEmpty()) {
                binding.recView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = MovieAdapter(movies)
                    setHasFixedSize(true)
                }
            }
        })
        return binding.root
    }
}
