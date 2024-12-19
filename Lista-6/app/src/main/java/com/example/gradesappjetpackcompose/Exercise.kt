package com.example.gradesappjetpackcompose

data class Exercise(
    val content: String,
    val points: Int
) {
    companion object {
        fun generateExercise(num: Int): MutableList<Exercise> {
            val letters = CharRange('A','Z').toMutableList()
            val exercises = mutableListOf<Exercise>()
            for(i in 0 until num) {
                val contentList = mutableListOf<String>()
                val randLength = (10..50).random()
                for (i in 0 until randLength) {
                    val randLetter = letters.random()
                    contentList.add(randLetter + "")
                }
                val finalContent = contentList.joinToString("")
                val finalPoints = (0..10).random()
                exercises.add(Exercise(finalContent,finalPoints))
            }
            return exercises
        }
    }
}