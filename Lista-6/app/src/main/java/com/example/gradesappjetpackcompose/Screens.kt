package com.example.gradesappjetpackcompose

sealed class Screens(val route: String) {
    data object E1 : Screens("e1")
    data object E2 : Screens("e2")
    data object E3 : Screens("e3")
}