package com.example.quiz

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

class MainActivity : AppCompatActivity() {

    private val questions = listOf( // https://matura100procent.pl/zadania-maturalne/baza-zadan-maturalnych/baza-zadan-z-fizyki/
        Question(
            "Przewodnik wykonany z miedzi dołączono do źródła prądu. Przepływ prądu w tym przewodniku polega na uporządkowanym ruchu",
            listOf("elektronów, a jego opór wraz ze wzrostem temperatury rośnie.",
                "elektronów, a jego opór wraz ze wzrostem temperatury maleje.",
                "jonów, a jego opór wraz ze wzrostem temperatury rośnie.",
                "jonów, a jego opór wraz ze wzrostem temperatury maleje."),
            0
        ),
        Question(
            "Spadochroniarz o masie 75 kg opada na spadochronie pionowo w dół z prędkością o stałej wartości 5 m/s. Siła oporów ruchu ma wartość około",
            listOf("25 N", "75 N", "250 N", "750 N"),
            0
        ),
        Question(
            "Rowerzysta jadący początkowo z prędkością 8 m/s rozpoczął zjazd z górki i przyśpieszył jednostajnie wzdłuż prostego zbocza do prędkości 10 m/s w czasie 4 sekund. Jeżeli łączna masa rowerzysty i roweru była równa 60 kg, to siła wypadkowa powodująca przyśpieszenie była równa",
            listOf("30 N", "120 N", "150 N", "270 N"),
            0
        ),
        Question(
            "Podczas odczytu za pomocą wiązki światła laserowego informacji zapisanych na płycie CD wykorzystywane jest zjawisko",
            listOf("polaryzacji", "odbicia", "załamania", "interferencji"),
            1
        ),
        Question(
            "Monochromatyczna wiązka światła wysłana przez laser pada prostopadle na siatkę dyfrakcyjną. Na ekranie położonym za siatką dyfrakcyjną możemy zaobserwować",
            listOf("jednobarwne prążki dyfrakcyjne.",
                "pojedyncze widmo światła białego.",
                "pojedynczy jednobarwny pas światła.",
                "widma światła białego ułożone symetrycznie względem prążka zerowego."),
            0
        ),
        Question(
            "Sprawność silnika cieplnego wynosi 20%. W ciągu 1 godziny silnik oddaje do chłodnicy 20 kJ energii. W tym czasie pobiera on z grzejnika energię cieplną o wartości",
            listOf("25 kJ", "40 kJ", "50 kJ", "100 kJ"),
            0
        ),
        Question(
            "Promienie słoneczne ogrzały szczelnie zamkniętą metalową butlę z gazem. Jeżeli pominiemy rozszerzalność termiczną butli, to gaz w butli uległ przemianie",
            listOf("izobarycznej", "izochorycznej", "izotermicznej", "adiabatycznej"),
            1
        ),
        Question(
            "Piłkę o masie 1 kg upuszczono swobodnie z wysokości 1 m. Po odbiciu od podłoża piłka wzniosła się na maksymalną wysokość 50 cm. W wyniku zderzenia z podłożem i w trakcie ruchu piłka straciła energię o wartości około",
            listOf("1 J", "2 J", "5 J", "10 J"),
            2
        ),
        Question(
            "Naładowana cząstka wpada w próżni w obszar jednorodnego pola prostopadle do linii tego pola. Cząstka w obszarze pola porusza się po okręgu. Opisana sytuacja może mieć miejsce w",
            listOf("polu magnetycznym", "polu grawitacyjnym", "polu elektrostatycznym", "każdym z trzech pól wyżej wymienionych"),
            0
        ),
        Question(
            "W półprzewodnikach domieszkowych typu n, w stosunku do półprzewodników samoistnych, mamy do czynienia z",
            listOf("niedoborem dziur", "nadmiarem dziur", "niedoborem elektronów", "nadmiarem elektronów"),
            3
        ),
    )

    private var questionCount = 0

    private fun loadQuestion(questionIndex: Int) {
        val radioButtons = listOf(R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4)
        var answerId = 0

        for (id in radioButtons) {
            val radioButton = findViewById<RadioButton>(id)
            radioButton.buttonTintList = ColorStateList.valueOf(Color.parseColor("#486405"))
            radioButton.text = questions[questionIndex].answers[answerId]
            answerId++
        }

        val questionTextView = findViewById<TextView>(R.id.question_content)
        questionTextView.text = questions[questionIndex].question

        val questionCounter = findViewById<TextView>(R.id.question_counter)
        questionCounter.text = "Pytanie ${questionIndex + 1}/${questions.size}"

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = questionIndex + 1
    }

    private var scoredPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scoredPointsText = findViewById<TextView>(R.id.scored_points)
        scoredPointsText.visibility = View.GONE

        loadQuestion(questionCount)
        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#dde6c5"))

        submitButton.setOnClickListener {
            val radioButtons = listOf(R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4)
            val selectedAnswerIndex = radioButtons.indexOfFirst { id ->
                findViewById<RadioButton>(id).isChecked
            }

            if (selectedAnswerIndex == questions[questionCount].correctAnswerIndex) {
                scoredPoints++
            }

            if (questionCount<questions.size-1) {
                questionCount++
                loadQuestion(questionCount)
            }
            else {
                submitButton.isEnabled = false
                findViewById<TextView>(R.id.question_counter).text = "Gratulacje"
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                findViewById<TextView>(R.id.question_content).visibility = View.GONE
                findViewById<RadioGroup>(R.id.radio_group).visibility = View.GONE
                findViewById<Button>(R.id.submit_button).visibility = View.GONE
                scoredPointsText.text = "Zdobyłeś ${scoredPoints*10} pkt"
                scoredPointsText.visibility = View.VISIBLE
            }
        }


    }
}
