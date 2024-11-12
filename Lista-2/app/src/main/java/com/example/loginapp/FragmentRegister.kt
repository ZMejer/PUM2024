package com.example.loginapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            val username = binding.loginInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val password2 = binding.passwordInput2.text.toString()
            val errorMessage = binding.ErrorMessage

            if (UserDatabase.register(username, password, password2)){
                val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
                Navigation.findNavController(requireView()).navigate(action)
            }
            else {
                errorMessage.text = "Użytkownik o takim loginie już istnieje lub hasła się nie zgadzają."
            }

        }

        binding.fabA.setOnClickListener {
            val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }




        return binding.root
    }
}