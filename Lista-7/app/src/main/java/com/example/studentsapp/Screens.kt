package com.example.studentsapp

sealed class Screens(val route: String) {
    data object StudentList : Screens("students")
    data object StudentDetails : Screens("details")
}