package com.example.gradesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ListBinding

    class WordListAdapter(// od
        private val exerciseList: MutableList<ExerciseList>,
        private val onItemClick: (String) -> Unit
        // do
        )
        : RecyclerView.Adapter<WordListViewHolder>() {
        // onCreateViewHolder, getItemCount, onBindViewHolder - metody klasy Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        return WordListViewHolder(
            ListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )//od
        {onItemClick(exerciseList[it].exercises.toString())}
            //do
    } // wywolywana gdy potrdzeba nowego widoku do wysweitlania

    override fun getItemCount(): Int {
        return exerciseList.size // liczba elem do wysweitlenia
    }

        override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
            val currentItem = exerciseList[position]
            val subjectName = currentItem.subject.name
            val listNumber = exerciseList.subList(0, position).count { it.subject == currentItem.subject } + 1
            val exercisesNumber = currentItem.exercises.size
            val grade = currentItem.grade
            holder.bind(subjectName,"Lista "+listNumber.toString(),"Liczba zadań: "+exercisesNumber.toString(),"Ocena: "+grade.toString())
        }

}
// kontekst co to i po co, cykle zycia