package com.example.lista8

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpdateScreen(arg: String?,onMainScreen: () -> Unit) {
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    
    val subject = remember { mutableStateOf("") }
    val grade = remember { mutableStateOf("") }

    LaunchedEffect(arg) {
        if (arg != null) {
            val gradeData = viewModel.getGradeById(arg.toInt())
            gradeData?.let {
                subject.value = it.subject
                grade.value = it.grade.toString()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().fillMaxWidth().padding(top=60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="Edytuj ocenę",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier.padding(top=100.dp).fillMaxSize(),
        ){
            OutlinedTextField(
                value = subject.value,
                onValueChange = { newSubject -> subject.value = newSubject },  // Change subject on input
                label = { Text("Nazwa przedmiotu", fontSize = 29.sp) },
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=15.dp,start=20.dp, end=20.dp),
                textStyle = TextStyle(fontSize = 29.sp)
            )
            OutlinedTextField(
                value = grade.value,
                onValueChange = { newText -> grade.value = newText },
                label = { Text("Ocena", fontSize = 29.sp) },
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=15.dp,start=20.dp, end=20.dp),
                textStyle = TextStyle(fontSize = 29.sp)
            )
            Column(modifier = Modifier.padding(start=20.dp,end = 20.dp, bottom= 30.dp,top=30.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = {
                        if (arg != null) {
                            viewModel.updateGrade(
                                Grade(
                                    arg.toInt(),
                                    subject.value,
                                    grade.value.toFloat()
                                )
                            )
                        }
                        onMainScreen()
                    },
                    modifier = Modifier.height(80.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("ZATWIERDŹ EDYCJĘ",fontSize = 29.sp)
                }
                Button(onClick = {
                    if (arg != null) {
                        viewModel.deleteGrade(Grade(arg.toInt(),"",0f))
                        onMainScreen()
                    }
                },
                    modifier = Modifier.height(80.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonColors(Color(0xFFbd4b4b),Color.White,Color(0xFFbd4b4b),Color(0xFFbd4b4b))){
                    Text("USUŃ", fontSize = 29.sp)
                }
            }
        }
    }
}