package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.databinding.FragmentABinding


class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentABinding.inflate(layoutInflater)

        binding.RegisterButton.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentRegister()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.LoginButton.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentLogin()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }

}