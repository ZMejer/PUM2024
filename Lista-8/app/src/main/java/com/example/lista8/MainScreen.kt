package com.example.lista8

import android.app.Application
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.absoluteValue

@Composable
fun MainScreen(onInsertScreen: () -> Unit, onUpdateScreen: (String) -> Unit){
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    val grades = viewModel.gradesState.collectAsStateWithLifecycle().value
    /*
    LaunchedEffect(Unit) {
        viewModel.addAllGrades(DataProvider.grades)
    }
    */
    Column(modifier = Modifier.fillMaxSize().fillMaxWidth().padding(top=60.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="Moje oceny",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(0.7f).padding(top=40.dp)
        ) {
            items(grades.size) {
                Card(
                    modifier = Modifier
                        .padding(bottom=15.dp,start=20.dp, end=20.dp)
                        .height(80.dp)
                        .border(2.dp, Color(0xFFb8a9c7), RoundedCornerShape(16.dp))
                        .clickable {onUpdateScreen(grades[it].id.toString())}
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = grades[it].subject,
                                fontSize = 29.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start=20.dp)
                            )
                            Text(
                                text = "${grades[it].grade} ${grades[it].id}",
                                fontSize = 29.sp,
                                textAlign = TextAlign.Right,
                                modifier = Modifier
                                    .padding(end = 20.dp)
                            )
                        }
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(bottom=30.dp,start=20.dp, end=20.dp, top=30.dp)
                .height(120.dp)
                .border(2.dp,Color(0xFFb8a9c7), RoundedCornerShape(16.dp))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Średnia ocen",
                        fontSize = 29.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${viewModel.average}",
                        fontSize = 29.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(start=20.dp,end = 20.dp, bottom= 30.dp)){
            Button(onClick = { onInsertScreen() },
                modifier = Modifier.height(80.dp).fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)) {
                Text("NOWY", fontSize = 29.sp)
            }
        }
    }
}

