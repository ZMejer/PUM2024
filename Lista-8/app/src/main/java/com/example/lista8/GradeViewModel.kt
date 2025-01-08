package com.example.lista8

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : ViewModel() {

    private val repository: GradeRepository
    private val _gradesState = MutableStateFlow<List<Grade>>(emptyList())
    val gradesState: StateFlow<List<Grade>>
        get() = _gradesState

    init {
        val db = GradeDatabase.getDatabase(application)
        val dao = db.gradeDao()
        repository = GradeRepository(dao)

        fetchGrades()
    }

    private fun fetchGrades() {
        viewModelScope.launch {
            repository.getGrades().collect { grades ->
                _gradesState.value = grades
            }
        }
    }

    fun clearGrades() {
        viewModelScope.launch {
            repository.clear()
        }
    }

    fun addGrade(grade: Grade) {
        viewModelScope.launch {
            repository.add(grade)
        }
    }

    fun addAllGrades(grades: List<Grade>) {
        viewModelScope.launch {
            repository.addAll(grades)
        }
    }
}