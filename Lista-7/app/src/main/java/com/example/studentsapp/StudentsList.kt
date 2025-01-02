package com.example.studentsapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsapp.ui.theme.StudentsAppTheme

@Composable
fun StudentsList() {
    Column(
        modifier = Modifier
            .fillMaxSize() 
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista studentów",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(20.dp)
        )
        LazyColumn {
            items(StudentsData.students.size) { index ->
                val student = StudentsData.students[index]
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth()){
                    Text(
                        text = "${student.name} ${student.surname}",
                        modifier=Modifier.padding(top=20.dp, start=20.dp,end=20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "numer indeksu: ${student.index}",
                        modifier=Modifier.padding(start=20.dp,bottom=20.dp,end=20.dp),
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentsPreview() {
    StudentsAppTheme {
        StudentsList()
    }
}
