package com.example.zozamax_app.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentTrailerBinding


class TrailerFragment : Fragment() {

    private lateinit var binding: FragmentTrailerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrailerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the VideoView
        val videoView: VideoView = binding.trailerVideo
        val videoUri: Uri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.trailer)

        videoView.setVideoURI(videoUri)
        videoView.setMediaController(MediaController(requireContext()))
        videoView.requestFocus()
        videoView.start()
    }
}

