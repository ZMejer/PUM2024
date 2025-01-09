package com.example.lista8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "grades_table")
data class Grade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val grade: Float,
) {
    constructor() : this(0, "", 0f)
}

object DataProvider {
    val grades = listOf(
        Grade(0,"Matematyka",4.0f),
        Grade(0,"PUM",5.0f),
        Grade(0,"Fizyka",3.5f),
        Grade(0,"PAM",4.5f)
    )

    val grade: Grade
        get() = Grade(0,"Test",1.0f)
}

@Dao
interface GradeDao {

    @Query("SELECT * FROM grades_table")
    fun getGrades(): Flow<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Grade)


    @Query("DELETE FROM grades_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(grade: Grade)

    @Update
    suspend fun update(grade: Grade)

    @Query("DELETE FROM grades_table WHERE id=:gradeId")
    fun deleteById(gradeId : Int)

}
