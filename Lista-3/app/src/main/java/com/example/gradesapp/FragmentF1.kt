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
            adapter = WordListAdapter(ExerciseList.Companion.ExerciseListProvider.allExerciseLists) { clickedItem ->
                val currentIndex = ExerciseList.Companion.ExerciseListProvider.allExerciseLists.indexOf(clickedItem)

                val listCount = ExerciseList.Companion.ExerciseListProvider.allExerciseLists
                    .subList(0, currentIndex + 1)
                    .count { it.subject == clickedItem.subject }

                val subj = clickedItem.subject.name
                val action = FragmentF1Directions.actionFragmentF1ToFragmentF3("$subj \n Lista $listCount")
                Navigation.findNavController(requireView()).navigate(action)
            }

            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }
}
// kontekst (requireContext())