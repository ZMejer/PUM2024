package com.example.studentsapp

data class Student(
    val index: String,
    val name: String,
    val surname: String,
    val average: Double,
    val year: Int
)
object StudentsData {
    val students = mutableListOf(
        Student("483703", "Jan", "Kowalski", 3.43, 2),
        Student("483704", "Anna", "Nowak", 4.15, 1),
        Student("483705", "Marek", "Wiśniewski", 2.85, 3),
        Student("483706", "Ewa", "Wójcik", 4.05, 2),
        Student("483707", "Paweł", "Kozłowski", 3.75, 4),
        Student("483708", "Kasia", "Mazur", 3.55, 1),
        Student("483709", "Michał", "Lewandowski", 4.25, 3),
        Student("483710", "Zuzanna", "Kamiński", 3.95, 2),
        Student("483711", "Adam", "Zieliński", 3.30, 4),
        Student("483712", "Magdalena", "Szymański", 4.00, 3),
        Student("483713", "Krzysztof", "Dąbrowski", 2.95, 2),
        Student("483714", "Joanna", "Jankowska", 4.50, 1),
        Student("483715", "Tomasz", "Kwiatkowski", 2.70, 4),
        Student("483716", "Karolina", "Jabłońska", 3.60, 2),
        Student("483717", "Piotr", "Wojciechowski", 3.80, 3),
        Student("483718", "Agnieszka", "Byk", 3.95, 1),
        Student("483719", "Jakub", "Kaczmarek", 4.10, 2),
        Student("483720", "Dominika", "Pawlak", 3.20, 4),
        Student("483721", "Łukasz", "Miller", 3.85, 3)
    )
}


