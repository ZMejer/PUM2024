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

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch {
            repository.delete(grade)
        }
    }

    private fun calculateAverage(): Float {
        val gradeNum = _gradesState.value.size
        if (gradeNum == 0) {
            return 0f
        }
        var gradeSum = 0f
        _gradesState.value.forEach { grade ->
            gradeSum += grade.grade
        }
        return gradeSum / gradeNum
    }

    val average: Float
        get() = calculateAverage()

}