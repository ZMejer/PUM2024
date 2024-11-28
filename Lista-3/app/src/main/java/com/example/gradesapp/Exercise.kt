package com.example.gradesapp

data class Exercise(
    val content: String,
    val points: Int
) {
    companion object {
        fun generateExercise(): Exercise {
            val letters = CharRange('A','Z').toMutableList()
            val randLength = (0..20).random()
            val contentList = mutableListOf<String>()
            for(i in 0 until randLength) {
                val randLetter = letters.random()
                contentList.add(randLetter+"")
            }
            val finalContent = contentList.joinToString("")
            val finalPoints = (0..10).random()

            return Exercise(finalContent,finalPoints)
        }
    }
}