package com.example.gradesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.GradesBinding


class GradesAdapter(private val wordList: List<Pair<String, Double>>) : RecyclerView.Adapter<GradesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesViewHolder {
        return GradesViewHolder(
                GradesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

        override fun getItemCount() = wordList.size

        override fun onBindViewHolder(holder: GradesViewHolder, position: Int) {
            val currentItem = wordList[position]
            holder.bind(currentItem.toString())
        }


}
