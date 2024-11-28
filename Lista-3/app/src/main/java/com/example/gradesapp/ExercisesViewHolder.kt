package com.example.gradesapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ExercisesBinding

class ExercisesViewHolder(private val binding: ExercisesBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(contentItem: String,pointsItem:String, idxItem:String){
        binding.singleWord.text = contentItem
        binding.exerciseCount.text = "Zadanie "+idxItem
        binding.points.text = "pkt: "+pointsItem
    }
}