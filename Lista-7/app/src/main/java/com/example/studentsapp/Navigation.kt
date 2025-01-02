package com.example.studentsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.StudentList.route) {
        composable(route = Screens.StudentList.route){
            val arg = 5
            StudentsList{navController.navigate(Screens.StudentDetails.route + "/$arg")}
        }

        composable(route = Screens.StudentDetails.route + "/{arg}"){
            val arg = it.arguments?.getString("arg")
            StudentDetails(arg) {navController.popBackStack()}
        }
    }
}