package com.example.lab3preliminaries.data

data class QuizUiState(
    val name: String = "Unknown",
    val introvertScore: Int = 0,
    val extrovertScore: Int = 0,
    val currentScore: Int = 0,
    val totalScore: Int = 0,
    val questionIndex: Int = 0
)

