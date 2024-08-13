package com.example.zozamax_app.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentMovieBinding
import com.example.zozamax_app.util.IMAGE_URL
import com.example.zozamax_app.util.SHARED_PREFS
import com.example.zozamax_app.util.USER_NAME
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.databinding.FragmentHomeBinding

private const val MOVIE = "movie"

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val args: MovieFragmentArgs by navArgs()
    private lateinit var firebaseAuth: DatabaseReference
    private lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        val view1 = FragmentHomeBinding.inflate(inflater, container, false)
        view1.image.setImageResource(R.drawable.baseline_person_24)

        pref = requireContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        firebaseAuth = FirebaseDatabase.getInstance().reference
         val movie: Result = args.movie

        val view = inflater.inflate(R.layout.fragment_transformation, container, false)
        val bottomSheet = BottomSheetDialog(requireContext())


        Handler(Looper.getMainLooper()).postDelayed({
            binding.transformationLayout.startTransformWithDelay(200)
            binding.transformationLayout.startTransform()
            binding.transformationLayout.bindTargetView(binding.image)
            Picasso.get().load(IMAGE_URL +movie!!.poster_path).into(binding.image)
        }, 1000)


        //binding data to the view
        binding.title.text = movie!!.title
        binding.overview.text = movie!!.overview
        binding.releaseDate.text = "Realeased on: ${movie!!.release_date}"

        //adding a movie to favorites
        binding.favs.setOnClickListener{
            if(pref.getString(USER_NAME, "") == ""){
                bottomSheet.setContentView(view)
                bottomSheet.show()
                //Toast.makeText(requireContext(), "Not logged in", Toast.LENGTH_LONG).show()
            }else{
                firebaseAuth.child("favorites")
                //Toast.makeText(requireContext(), "logged in", Toast.LENGTH_LONG).show()

            }

        }

        return binding.root
    }
    companion object{
        @JvmStatic
        fun newInstance(movie: Result) = MovieFragment().apply{
            arguments = Bundle().apply {
                putSerializable(MOVIE, movie)
            }
        }
    }


}
