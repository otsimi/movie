package com.example.zozamax_app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.adapters.MovieAdapter
import com.example.zozamax_app.databinding.FragmentPopularBinding
import com.example.zozamax_app.repository.PopularMovieRepo
import com.example.zozamax_app.viewmodel.PopularModelProvider
import com.example.zozamax_app.viewmodel.PopularViewModel
import com.skydoves.transformationlayout.onTransformationStartContainer

private const val TAG = "popularMovies"

class  PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private var _binding: FragmentPopularBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTransformationStartContainer()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPopularBinding.inflate(inflater, container, false)

        val repo = PopularMovieRepo()

        val productViewModel: PopularViewModel by viewModels {
            PopularModelProvider(repo)
        }

        productViewModel.popularMovies.observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "Popular movies -> $movies")
            if(movies.isNotEmpty()){
                binding.recView.apply {

                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = MovieAdapter(movies)
                    setHasFixedSize(true)
                }
            }
        })

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        super.onDestroyView()
        _binding = null
    }
}
