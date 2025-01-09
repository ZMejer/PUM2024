package com.example.lista8

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Update

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route){
            MainScreen(
                onInsertScreen = {navController.navigate(Screens.InsertScreen.route)},
                onUpdateScreen = {idx -> navController.navigate(Screens.UpdateScreen.route+"/$idx")}
            )
        }

        composable(route = Screens.InsertScreen.route){
            InsertScreen{navController.popBackStack()}
        }

        composable(route = Screens.UpdateScreen.route + "/{arg}"){ backStackEntry ->
            val arg = backStackEntry.arguments?.getString("arg")
            UpdateScreen(arg){navController.popBackStack()}

        }
    }
}