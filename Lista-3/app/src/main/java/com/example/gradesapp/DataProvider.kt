package com.example.gradesapp

import kotlin.math.round

object DataProvider {
    val wordList: List<Triple<String, Double, Int>> = calculateAverages()

    private fun calculateAverages(): List<Triple<String, Double, Int>> {
        return ExerciseList.Companion.ExerciseListProvider.allExerciseLists
            .groupBy { it.subject.name }
            .map { (subjectName, exercises) ->
                val totalPoints = exercises.sumOf { it.grade }
                val average = if (exercises.isNotEmpty()) {
                    round((totalPoints / exercises.size) * 100) / 100
                } else {
                    0.0
                }
                val exerciseCount = exercises.size
                Triple(subjectName, average, exerciseCount)
            }
    }
}
