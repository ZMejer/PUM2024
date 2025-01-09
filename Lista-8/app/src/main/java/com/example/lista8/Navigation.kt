package com.example.lista8

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route){
            MainScreen{navController.navigate(Screens.InsertScreen.route)}
        }

        composable(route = Screens.InsertScreen.route){
            InsertScreen{navController.popBackStack()}
        }
    }
}