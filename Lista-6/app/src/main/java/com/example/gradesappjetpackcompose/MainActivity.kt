package com.example.gradesappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
}
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.E1.route
    ) {
        composable(route = Screens.E1.route) { E1(onE3 = { listNumber, subject, index ->
            navController.navigate("${Screens.E3.route}/$listNumber/$subject/$index")
        }) }
        composable(
            route = "${Screens.E3.route}/{listNumber}/{subject}/{index}",
            arguments = listOf(
                navArgument("listNumber") { type = NavType.IntType },
                navArgument("subject") { type = NavType.StringType },
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val listNumber = backStackEntry.arguments?.getInt("listNumber") ?: 0
            val subject = backStackEntry.arguments?.getString("subject") ?: ""
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            E3(listNumber, subject,index)
        }
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
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

    }
}

@Composable
fun E3(listNumber: Int, subject: String, index: Int) {
    val exercises = ExerciseList.Companion.ExerciseListProvider.allExerciseLists[index].exercises
    Column(
        Modifier.fillMaxSize().padding(top = 50.dp, bottom = 130.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = subject,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Lista $listNumber",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        LazyColumn {
            items(exercises.size) { idx ->
                val points = exercises[idx].points
                val content = exercises[idx].content
                Card(
                    modifier = Modifier.
                    fillMaxWidth(0.9f).
                    padding(top=25.dp).
                    border(BorderStroke(3.dp, Color(0xFFedeace)), RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFAF9F1)
                    ),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Text(text="pkt: $points", modifier = Modifier.padding(20.dp).fillMaxWidth(),
                        fontSize = 22.sp, textAlign = TextAlign.Right,)
                    Text(text="Zadanie ${idx+1}", modifier = Modifier.padding(start = 20.dp),
                        fontSize = 30.sp)
                    Text(text=content, modifier = Modifier.padding(20.dp),
                        fontSize = 20.sp)

                }
            }
        }

    }
}
@Composable
fun E1(onE3: (Int, String, Int) -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(top = 50.dp, bottom = 130.dp),
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
                    onClick = { onE3(listNumber, subjectName, index) }
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
                    Spacer(modifier = Modifier.height(20.dp))
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