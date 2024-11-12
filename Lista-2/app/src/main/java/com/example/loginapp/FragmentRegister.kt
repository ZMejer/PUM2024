package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.databinding.FragmentRegisterBinding

class FragmentRegister : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.RegisterSubmitButton.setOnClickListener {
            val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.fabA.setOnClickListener {
            val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }
}