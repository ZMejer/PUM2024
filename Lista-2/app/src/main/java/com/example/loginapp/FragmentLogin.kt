package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.RegisterBtn.setOnClickListener {
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.fabA.setOnClickListener {
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.LoginSubmitButton.setOnClickListener {
            val username = binding.loginLoginInput.text.toString()
            val password = binding.loginPasswordInput.text.toString()

            if(UserDatabase.login(username,password)){
                val action = FragmentLoginDirections.actionFragmentLoginToFragmentWelcome(username)
                Navigation.findNavController(requireView()).navigate(action)
            }
            else {
                binding.LoginErrorMessage.text = "Błędne hasło lub użytkownik nie istnieje."
            }
        }

        return binding.root
    }

}