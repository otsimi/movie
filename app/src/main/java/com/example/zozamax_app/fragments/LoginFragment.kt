package com.example.zozamax_app.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val navController = binding.root.findNavController()
            navController.navigate(R.id.action_loginFragment_to_movie1Fragment)
        }, 2000)
        return binding.root
    }
}

