package com.example.studentsapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private var _students = mutableStateListOf<Student>()
    val students: List<Student>
        get() = _students

    init {
        reinitialize()
    }

    fun reinitialize(){
        _students.clear()
        _students.addAll(StudentsData.students)
    }
}