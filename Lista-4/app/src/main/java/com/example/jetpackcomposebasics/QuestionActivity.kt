package com.example.jetpackcomposebasics

import android.content.Context
import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

class QuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadQuestion()
                }
            }
        }
    }
}

var finalScoredPoints = 0

@Composable
fun LoadQuestion() {
    val context = LocalContext.current
    val idx = remember { mutableStateOf(0) }
    val isSelected = remember {mutableStateOf(-1)}
    val scoredPoints = remember { mutableStateOf(0) }
    var selectedAnswerIndex = -1
    if (idx.value<Questions.questionsList.size) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Pytanie ${idx.value + 1}/${Questions.questionsList.size}",
                fontSize = 30.sp,
            )

            LinearProgressIndicator(
                progress = { (idx.value.toFloat() + 1) / Questions.questionsList.size.toFloat() },
                modifier = Modifier.fillMaxWidth(0.5f)
                    .size(width = 100.dp, height = 10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = Color(0xFF486405)
            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0E0E0)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = Questions.questionsList[idx.value].question,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth(0.8f).padding(12.dp)
                )

            }


            Card(
                modifier = Modifier.fillMaxWidth(0.8f)
                    .border(BorderStroke(3.dp, Color(0xFFedeace)), RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFAF9F1)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {

                Questions.questionsList[idx.value].answers.forEachIndexed { i, answer ->
                    Row {
                        RadioButton(
                            selected = isSelected.value == i,
                            onClick = { isSelected.value = i
                                        selectedAnswerIndex = i}
                        )
                        Text(text= answer, modifier=Modifier.padding(10.dp), fontSize = 20.sp)
                    }

                }
            }
                Button(
                    onClick = {
                        if (selectedAnswerIndex == Questions.questionsList[idx.value].correctAnswerIndex) {
                            scoredPoints.value += 1
                        }
                        idx.value += 1
                        isSelected.value = -1
                    },
                    modifier = Modifier.fillMaxWidth(0.8f).size(width = 100.dp, height = 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFdde6c5),
                        contentColor = Color(0xFF000000)
                    ),
                    shape = RoundedCornerShape(10),
                ) {
                    Text(text = "Następne", fontSize = 25.sp)
                }
                Spacer(modifier = Modifier.weight(1f))

        }
    }
        else {
            openHomepage(context)
            idx.value = 0
            finalScoredPoints = scoredPoints.value
        }
    }


private fun openHomepage(context: Context){
    val intent = Intent(context, ResultActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeBasicsTheme {
        LoadQuestion()
    }
}

