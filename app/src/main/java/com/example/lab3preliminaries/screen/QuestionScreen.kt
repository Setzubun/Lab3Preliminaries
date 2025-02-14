package com.example.lab3preliminaries.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab3preliminaries.data.Option
import com.example.lab3preliminaries.data.Question
import com.example.lab3preliminaries.data.TestData
import com.example.lab3preliminaries.ui.theme.Lab3PreliminariesTheme

@Composable
fun QuestionScreen(
    navController: NavController,
    quizViewModel: QuizViewModel,
    modifier: Modifier = Modifier,
){
    val uiState by quizViewModel.uiState.collectAsState()

    val currentQuestionIndex = uiState.questionIndex
    val currentQuestion = TestData.testData[currentQuestionIndex]
    val totalQuestions = TestData.testData.size
    var selectedOption: Option? by remember { mutableStateOf(null) }

    fun moveToNextQuestion(){
        quizViewModel.updateScore(selectedOption, currentQuestion)
        val allQuestionsAnswered = currentQuestionIndex + 1 == totalQuestions

        if (allQuestionsAnswered){
            navController.navigate("completed_screen")
            quizViewModel.calculateScore()
        } else {
            quizViewModel.incrementQuestionIndex()
            selectedOption = null
        }
    }

    fun resetQuiz(){
        selectedOption = null
        quizViewModel.reset()
        navController.navigate("start_screen")
    }

    Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            QuestionHeader(currentQuestionIndex, currentQuestion)
            AnswerOptions(selectedOption, onOptionSelected = { selectedOption = it}, currentQuestion)
            ButtonRow({ resetQuiz() }, { moveToNextQuestion() }, selectedOption)
        }
    }
}

@Composable
fun QuestionHeader(index: Int, question: Question, modifier: Modifier = Modifier){
    val questionNumber = index + 1
    Column (modifier){
        Text(text = "Question $questionNumber", style = MaterialTheme.typography.headlineLarge)
        Text(text = question.text, style = MaterialTheme.typography.headlineSmall)
        HorizontalDivider(thickness = 2.dp)
    }
}

@Composable
fun AnswerOptions(
    selectedOption: Option?,
    onOptionSelected: (Option) -> Unit,
    question: Question,
    modifier: Modifier = Modifier){
    Column (modifier.fillMaxWidth()){
        question.options.forEach{ option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 8.dp)
            ){
                RadioButton(
                    selected = (selectedOption == option),
                    onClick = {onOptionSelected(option)}
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = option.label)
            }
        }
    }
}

@Composable
fun ButtonRow(
    onCancel: () -> Unit,
    onNextQuestion: () -> Unit,
    selectedOption: Option?,
    modifier: Modifier = Modifier
    ){
    Row(modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = {
            onCancel()
        },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Cancel")
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Button(onClick = {
            if (selectedOption != null){
                onNextQuestion()
            }
        },
            modifier = Modifier.weight(1f)) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    Lab3PreliminariesTheme {
        val navController = rememberNavController()
        val quizViewModel: QuizViewModel = viewModel()
        QuestionScreen(navController, quizViewModel)
    }
}
