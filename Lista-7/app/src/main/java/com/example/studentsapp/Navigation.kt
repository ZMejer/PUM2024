package com.example.studentsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.StudentList.route) {
        composable(route = Screens.StudentList.route) {
            StudentsList { studentIndex ->
                navController.navigate(Screens.StudentDetails.route + "/$studentIndex")
            }
        }
        composable(route = Screens.StudentDetails.route + "/{arg}") { backStackEntry ->
            val arg = backStackEntry.arguments?.getString("arg")
            StudentDetails(arg) { navController.popBackStack() }
        }
    }
}