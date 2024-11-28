package com.example.gradesapp

import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ListBinding

class WordListViewHolder(
    private val binding: ListBinding,
    // od
    onItemClick: (Int) -> Unit // bo list.xml, WordListItemBinding na github
    // do
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(subjectItem: String, listNumberItem: String, exercisesNumberItem:String, gradeItem: String) {
        binding.subject.text = subjectItem // laczenie elementu (TextView w list.xml) z ViewHolder
        binding.listNumber.text = listNumberItem
        binding.exercisesNumber.text = exercisesNumberItem
        binding.grade.text = gradeItem
    }
    // od
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
    // do
}