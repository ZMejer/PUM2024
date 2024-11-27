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

    fun bind(item: String) {
        binding.singleWord.text = item // laczenie elementu (TextView w list.xml) z ViewHolder
    }
    // od
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
    // do
}