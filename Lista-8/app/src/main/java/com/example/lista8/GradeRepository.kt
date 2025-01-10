package com.example.lista8

import kotlinx.coroutines.flow.Flow

class GradeRepository(private val gradeDao: GradeDao) {

    fun getGrades() = gradeDao.getGrades()

    suspend fun clear() = gradeDao.deleteAll()

    suspend fun add(grade: Grade) = gradeDao.insert(grade)

    suspend fun addAll(grades: List<Grade>) {
        grades.forEach { grade ->
            gradeDao.insert(grade)
        }
    }

    // suspend fun delete(id: Int) = gradeDao.deleteById(id)

    suspend fun delete(grade: Grade) = gradeDao.delete(grade)

    suspend fun update(grade: Grade) = gradeDao.update(grade)
}
