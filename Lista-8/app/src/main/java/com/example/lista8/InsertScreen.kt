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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InsertScreen(onMainScreen: () -> Unit) {
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    val subject = remember { mutableStateOf("") }
    val grade = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().fillMaxWidth().padding(top=60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="Dodaj ocenę",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier.padding(top=100.dp).fillMaxSize(),
        ){
        OutlinedTextField(
            value = subject.value,
            onValueChange = { newSubject -> subject.value = newSubject },
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
        Column(modifier = Modifier.padding(start=20.dp,end = 20.dp, bottom= 30.dp,top=30.dp)) {
            Button(
                onClick = {
                    viewModel.addGrade(Grade(0,subject.value,grade.value.toFloat()))
                    onMainScreen()
                },
                modifier = Modifier.height(80.dp).fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("DODAJ",fontSize = 29.sp)
            }
        }
        }
    }

}
