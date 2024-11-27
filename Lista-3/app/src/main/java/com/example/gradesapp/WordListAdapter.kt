package com.example.gradesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ListBinding

    class WordListAdapter(// od
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
        {onItemClick(DataProvider.wordList[it])}
            //do
    } // wywolywana gdy potrdzeba nowego widoku do wysweitlania

    override fun getItemCount(): Int {
        return DataProvider.wordList.size // liczba elem do wysweitlenia
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currentItem = DataProvider.wordList[position]
        holder.bind(currentItem) // ustawia dane modelu (tutaj pozycje)
    }
}
// kontekst co to i po co, cykle zycia