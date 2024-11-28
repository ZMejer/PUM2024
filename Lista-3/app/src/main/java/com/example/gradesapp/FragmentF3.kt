package com.example.gradesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.databinding.FragmentF3Binding

class FragmentF3 : Fragment() {

    private lateinit var binding: FragmentF3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentF3Binding.inflate(inflater)
        binding.recyclerView.apply {
            adapter = ExercisesAdapter(ExercisesData.wordList)
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.subject.text=arguments?.getString("subject").toString()
        return binding.root
    }
}