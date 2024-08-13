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
import com.example.zozamax_app.databinding.FragmentUpcomingBinding
import com.example.zozamax_app.repository.UpcomingMovieRepo
import com.example.zozamax_app.viewmodel.UpcomingModelProvider
import com.example.zozamax_app.viewmodel.UpcomingViewModel
import com.skydoves.transformationlayout.onTransformationStartContainer

private const val TAG = "upcomingMovies"

class  UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding
    private var _binding: FragmentUpcomingBinding? = null

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

        binding = FragmentUpcomingBinding.inflate(inflater, container, false)

        val repo = UpcomingMovieRepo()

        val productViewModel: UpcomingViewModel by viewModels {
            UpcomingModelProvider(repo)
                 }

        productViewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "Upcoming movies -> $movies")
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
