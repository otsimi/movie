package com.example.zozamax_app.fragments


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
import com.example.zozamax_app.databinding.FragmentOnTvBinding
import com.example.zozamax_app.repository.OnTvMovieRepo
import com.example.zozamax_app.viewmodel.OnTvViewModel
import com.skydoves.transformationlayout.onTransformationStartContainer

private const val TAG  = "OnTv Movies"
class OnTvFragment : Fragment() {
    private lateinit var binding: FragmentOnTvBinding
    private var _binding: FragmentOnTvBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTransformationStartContainer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnTvBinding.inflate(inflater, container, false)

        val repo = OnTvMovieRepo()

        val productViewModel: OnTvViewModel by viewModels  {
            OnTvViewModel.OnTvModelProvider(repo)
        }
        productViewModel.onTvMovies.observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "OnTv Movies -> $movies")
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