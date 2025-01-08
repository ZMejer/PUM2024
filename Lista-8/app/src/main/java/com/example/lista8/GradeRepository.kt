package com.example.lista8

class GradeRepository(private val gradeDao: GradeDao) {

    fun getGrades() = gradeDao.getGrades()

    suspend fun clear() = gradeDao.deleteAll()

    suspend fun add(user: Grade) = gradeDao.insert(user)

    suspend fun addAll(grades: List<Grade>) {
        grades.forEach { grade ->
            gradeDao.insert(grade)
        }
    }
}
