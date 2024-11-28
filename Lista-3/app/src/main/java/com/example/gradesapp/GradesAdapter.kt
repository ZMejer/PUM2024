package com.example.gradesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.GradesBinding


class GradesAdapter(private val wordList: List<Triple<String, Double, Int>>) : RecyclerView.Adapter<GradesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesViewHolder {
        return GradesViewHolder(
                GradesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

        override fun getItemCount() = wordList.size

        override fun onBindViewHolder(holder: GradesViewHolder, position: Int) {
            val currentItem = wordList[position]
            val subject = currentItem.first.toString()
            val average = currentItem.second.toString()
            val num = currentItem.third.toString()
            holder.bind(subject,"Średnia: "+average,"Liczba list: "+num)
        }


}
