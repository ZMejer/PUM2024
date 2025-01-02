package com.example.studentsapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentsapp.ui.theme.StudentsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    // Stan dla pola tekstowego
    val word = remember { mutableStateOf("") }
    // Uzyskujemy instancję ViewModel
    val viewModel: WordViewModel = viewModel()

    Column(modifier = Modifier.padding(16.dp)) {
        // Wiersz z TextField i przyciskiem "ADD"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 8.dp),
                value = word.value, // Pobieranie wartości z `word`
                onValueChange = { word.value = it }, // Ustawienie nowej wartości
                label = { Text("New Word") }
            )

            Button(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    // Dodaj słowo do listy, jeśli pole nie jest puste
                    if (word.value.isNotEmpty()) {
                        viewModel.addWord(word.value)
                        word.value = "" // Czyścimy pole po dodaniu
                    }
                }
            ) {
                Text(text = "ADD")
            }
        }

        // Wyświetlanie listy słów
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.wordList.size) { index ->
                Text(
                    text = viewModel.wordList[index],
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        // Przycisk do wyczyszczenia listy
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { viewModel.clear() }
        ) {
            Text(text = "CLEAR")
        }

        // Przycisk do resetowania listy do wartości początkowych
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { viewModel.reinitialize() }
        ) {
            Text(text = "RESET")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudentsAppTheme {
        ListScreen()
    }
}
