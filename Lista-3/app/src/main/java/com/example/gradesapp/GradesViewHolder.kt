package com.example.gradesapp
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.GradesBinding


class GradesViewHolder(private val binding: GradesBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(item: String){
        binding.singleWord.text = item
    }
}