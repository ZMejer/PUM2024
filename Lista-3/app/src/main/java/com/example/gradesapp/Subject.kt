package com.example.gradesapp

data class Subject(
    val name: String
)
object Subjects {
    val subjectsList = mutableListOf(
        Subject("Matematyka"),
        Subject("PUM"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
    )
}