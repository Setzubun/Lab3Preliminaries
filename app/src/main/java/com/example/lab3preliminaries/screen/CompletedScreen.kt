package com.example.lab3preliminaries.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab3preliminaries.ui.theme.Lab3PreliminariesTheme

@Composable
fun CompletedScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = viewModel()
){
    val uiState = quizViewModel.uiState.collectAsState().value

    Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Column(modifier = modifier
                .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Text(text = "Completed!", style = MaterialTheme.typography.headlineLarge)
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Hi ${uiState.name},") // Receive User Input from StartScreen
                Text("You are ${uiState.introvertScore}% introvert, and ${uiState.extrovertScore}% extrovert.")
            }
            Column (modifier = modifier
                .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Spacer(modifier = Modifier.padding(16.dp))
                Button(onClick = { /*TODO*/ }){
                    Text(text = "Share your result")
                }
                Button(onClick = {
                    navController.navigate("question_screen")
                    val name = uiState.name
                    quizViewModel.reset()
                    quizViewModel.setName(name)
                }){
                    Text(text = "Restart the test")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedScreenPreview() {
    Lab3PreliminariesTheme {
        val navController = rememberNavController()
        CompletedScreen(
            navController = navController
        )
    }
}