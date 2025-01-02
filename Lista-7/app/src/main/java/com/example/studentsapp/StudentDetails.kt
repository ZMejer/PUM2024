package com.example.studentsapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StudentDetails(arg: String?, onBack: () -> Unit) {
    val viewModel: StudentViewModel = viewModel()
    val student = viewModel.students.find { it.index == arg }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

            if (student != null) {
                Text(
                    text = "${student.name} ${student.surname}",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(30.dp)
                )

                Text(
                    text = "Numer indeksu: $arg",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start=30.dp)
                )
                Text(
                    text = "Średnia ocen: ${student.average}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start=30.dp)
                )
                Text(
                    text = "Rok studiów: ${student.year}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start=30.dp, bottom=30.dp)

                )
            }
        }
        /*
        Button(onClick = { onBack() }) {
            Text("Go Back")
        }
        */
    }
}
