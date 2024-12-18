package com.example.jetpackcomposenavigationbasics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposenavigationbasics.ui.theme.JetpackComposeNavigationBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeNavigationBasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}
/*
@Composable
fun MainScreen(onSecondScreen: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onSecondScreen) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(arg: String?, onHome: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Second Screen. Argument: $arg")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onHome) { Text("Go back to Main Screen") }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route){
            val arg = 5
            MainScreen{navController.navigate(Screens.SecondScreen.route + "/$arg")}
        }

        composable(route = Screens.SecondScreen.route + "/{arg}"){
            val arg = it.arguments?.getString("arg")
            SecondScreen(arg) {navController.popBackStack()}
        }
    }
}
*/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { BottomNavGraph(navController = navController) }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route){ HomeScreen() }
        composable(route = Screens.FirstScreen.route){ FirstScreen() }
        composable(route = Screens.SecondScreen.route){ SecondScreen() }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.Home, BottomBar.First, BottomBar.Second
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@Composable
fun HomeScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp
        )
    }
}

@Composable
fun FirstScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "First Screen",
            fontSize = 40.sp
        )
    }
}

@Composable
fun SecondScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Second Screen",
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    JetpackComposeNavigationBasicsTheme {
        SecondScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    JetpackComposeNavigationBasicsTheme {
        FirstScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JetpackComposeNavigationBasicsTheme {
        HomeScreen()
    }
}
