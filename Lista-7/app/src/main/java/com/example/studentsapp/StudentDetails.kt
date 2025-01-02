package com.example.studentsapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentDetails(arg: String?, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Student Details for ID: $arg",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        /*
        Button(onClick = { onBack() }) {
            Text("Go Back")
        }
        */
    }
}
