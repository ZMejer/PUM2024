package com.example.gradesappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gradesappjetpackcompose.ui.theme.GradesAppJetpackComposeTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GradesAppJetpackComposeTheme {
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { BottomNavGraph(navController = navController) }
    )
    /*
    NavHost(navController = navController, startDestination = Screens.E1.route) {
        composable(route = Screens.E1.route){
            E1{navController.navigate(Screens.E3.route)}
        }
    }
    */

}
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.E1.route
    ) {
        composable(route = Screens.E1.route){ E1() }
        composable(route = Screens.E2.route){ E2() }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.E1, BottomBar.E2
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@Composable
fun E2() {
    val grades: List<Triple<String, Double, Int>> = calculateAverages()
    Column(
        Modifier.fillMaxSize().padding(top = 50.dp, bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Text(
            text = "Moje oceny",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn {
            items(grades.size) { index ->
                val grade = grades[index]

                Card(
                    modifier = Modifier.
                    fillMaxWidth(0.9f).
                    border(BorderStroke(3.dp, Color(0xFFedeace)), RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFAF9F1)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ){
                    Row(modifier = Modifier.fillMaxWidth()){
                        Text(text=grade.first,
                            fontSize=28.sp,
                            modifier = Modifier
                                .weight(1f).padding(10.dp))
                        Text(text="Średnia: " + grade.second.toString(),
                            fontSize=28.sp,
                            modifier = Modifier
                                .padding(10.dp))
                    }
                    Text(text="Liczba list: " + grade.third.toString(),
                        fontSize=22.sp,
                        modifier = Modifier
                            .padding(10.dp))
                }
                if (index < grades.size - 1) {
                    Spacer(modifier = Modifier.height(20.dp))  // Adjust the height as needed
                }
            }
        }

    }
}

@Composable
fun E3() {
    Text(text="LOL")
}

@Composable
fun E1() {
    Column(
        Modifier.fillMaxSize().padding(top = 50.dp, bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        val exercises = ExerciseList.Companion.ExerciseListProvider.allExerciseLists
        Text(
            text = "Moje listy zadań",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyColumn {
            items(exercises.size) { index ->
                val listNumber = exercises.subList(0, index).count { it.subject == exercises[index].subject } + 1
                val exercise = exercises[index]
                val num_of_exercises = exercise.exercises.size
                val subjectName = exercise.subject.name
                val grade = exercise.grade
                Card(
                    modifier = Modifier.
                    fillMaxWidth(0.9f).
                    border(BorderStroke(3.dp, Color(0xFFedeace)), RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFAF9F1)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    //onClick = onE3
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "$subjectName",
                            fontSize = 32.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp)
                        )
                        Text(
                            text = "Lista $listNumber",
                            fontSize = 32.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "Liczba zadań: $num_of_exercises",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .weight(1f)
                                .padding(10.dp)
                        )
                        Text(
                            text = "Ocena: $grade",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }

                }
                if (index < exercises.size - 1) {
                    Spacer(modifier = Modifier.height(20.dp))  // Adjust the height as needed
                }
            }
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun E1Preview() {
    GradesAppJetpackComposeTheme {
        E1()
    }
}
*/
@Preview(showBackground = true)
@Composable
fun NavPreview() {
    GradesAppJetpackComposeTheme {
        Navigation()
    }
}

private fun calculateAverages(): List<Triple<String, Double, Int>> {
    return ExerciseList.Companion.ExerciseListProvider.allExerciseLists
        .groupBy { it.subject.name }
        .map { (subjectName, exercises) ->
            val totalPoints = exercises.sumOf { it.grade }
            val average = if (exercises.isNotEmpty()) {
                round((totalPoints / exercises.size) * 100) / 100
            } else {
                0.0
            }
            val exerciseCount = exercises.size
            Triple(subjectName, average, exerciseCount)
        }
}