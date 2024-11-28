package com.example.gradesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentF1Binding

class FragmentF1 : Fragment() {

    private lateinit var binding: FragmentF1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentF1Binding.inflate(inflater)

        binding.recyclerView.apply {
            // od
            adapter = WordListAdapter(ExerciseList.allExerciseLists){
                Toast.makeText(requireContext(), "Clicked + $it", Toast.LENGTH_SHORT).show()
            }
            // do
            layoutManager = LinearLayoutManager(requireContext()) // kontekst
        }

        return binding.root
    }
}