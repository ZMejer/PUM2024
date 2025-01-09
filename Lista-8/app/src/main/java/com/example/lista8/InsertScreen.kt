package com.example.lista8

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val grades = viewModel.gradesState.collectAsStateWithLifecycle().value

    val subject = remember { mutableStateOf("") }
    val grade = remember { mutableStateOf("") }
    Column(){
        OutlinedTextField(
            value = subject.value,
            onValueChange = { newSubject -> subject.value = newSubject },
            label = { Text("Nazwa przedmiotu") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = grade.value,
            onValueChange = { newText -> grade.value = newText },
            label = { Text("Ocena") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addGrade(Grade(0,subject.value,grade.value.toFloat()))
                onMainScreen()
            }
        ) {
            Text("ZATWIERDŹ")
        }
    }

}
