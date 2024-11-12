package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.databinding.FragmentABinding
import com.example.loginapp.databinding.FragmentBBinding


class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBBinding.inflate(layoutInflater)
        binding.textView.text=arguments?.getInt("value").toString()

        binding.fabB.setOnClickListener {
            val action = FragmentBDirections.actionFragmentBToFragmentA(5)
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }

}