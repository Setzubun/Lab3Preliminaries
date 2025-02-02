package com.example.lab3preliminaries.screen

import androidx.lifecycle.ViewModel
import com.example.lab3preliminaries.data.Option
import com.example.lab3preliminaries.data.QuizUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.lab3preliminaries.data.Question

class QuizViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update{ currentState ->
            currentState.copy(name = name)
        }
    }

    fun incrementQuestionIndex(){
        _uiState.update{ currentState ->
            currentState.copy(questionIndex = currentState.questionIndex + 1)
        }
    }

    fun calculateScore(){
        val extrovertScore = _uiState.value.currentScore.toFloat() / _uiState.value.totalScore.toFloat() * 100
        val intExtrovertScore = extrovertScore.toInt()
        val introvertScore = 100 - intExtrovertScore

        _uiState.update{ currentState ->
            currentState.copy(
                introvertScore = introvertScore,
                extrovertScore = intExtrovertScore
            )
        }
    }

    fun updateScore(option: Option?, question: Question){
        val maxScore = question.options.maxOf { it.score }
        if (option != null) {
            _uiState.update{ currentState ->
                currentState.copy(
                    currentScore = currentState.currentScore + option.score,
                    totalScore = currentState.totalScore + maxScore
                )
            }
        }
    }

    fun reset(){
        _uiState.value = QuizUiState()
    }

}