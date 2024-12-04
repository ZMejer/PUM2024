package com.example.jetpackcomposebasics

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
object Questions {
    val questionsList = mutableListOf(
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
            "Rowerzysta jadący początkowo z prędkością 8 m/s przyśpieszył jednostajnie do prędkości 10 m/s w czasie 4 sekund. Jeżeli łączna masa rowerzysty i roweru była równa 60 kg, to siła wypadkowa powodująca przyśpieszenie była równa",
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
}