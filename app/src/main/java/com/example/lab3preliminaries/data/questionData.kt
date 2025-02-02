package com.example.lab3preliminaries.data

data class Question(
    val text: String,
    val options: List<Option>
)

data class Option(
    val label: String,
    val score: Int
)

object TestData {
    val testData = listOf(
        Question(
            text = "How do you feel about social events?",
            options = listOf(
                Option("Extremely excited! Sign me up for everything", 20),
                Option("Interested in a few, but not too overwhelming", 10),
                Option("Prefer to focus on studies and personal time", 0),
            ),
        ),
        Question(
            text = "How much time do you need to recharge after socializing?",
            options = listOf(
                Option("I feel energized after social events", 20),
                Option("I need a bit of time to recover", 10),
                Option("I prefer alone time for a long time", 0),
            ),
        ),
        Question(
            text = "Being around people make me feel...",
            options = listOf(
                Option("A bit exhausted. Being around others can be draining", 0),
                Option("energized for a while, but there are times when I'd rather be alone", 10),
                Option("Like I'm in my element!", 20)
            )
        )
    )
}