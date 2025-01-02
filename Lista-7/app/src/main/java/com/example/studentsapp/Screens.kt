package com.example.studentsapp

sealed class Screens(val route: String) {
    data object StudentsScreen : Screens("students")
    data object DetailsScreen : Screens("details")
}