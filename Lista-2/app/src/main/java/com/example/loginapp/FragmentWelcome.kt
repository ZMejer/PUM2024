package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.databinding.FragmentWelcomeBinding


class FragmentWelcome : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)

        binding.textView2.text="Witaj "+arguments?.getString("uname").toString()

        binding.LogoutButton.setOnClickListener {
            val action = FragmentWelcomeDirections.actionFragmentWelcomeToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }

}