package com.example.lista8

sealed class Screens(val route: String) {
    data object MainScreen : Screens("main_screen")
    data object InsertScreen : Screens("insert_screen")
    data object UpdateScreen : Screens("update_screen")
}