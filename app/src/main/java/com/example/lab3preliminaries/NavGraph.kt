package com.example.lab3preliminaries

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab3preliminaries.screen.CompletedScreen
import com.example.lab3preliminaries.screen.QuestionScreen
import com.example.lab3preliminaries.screen.QuizViewModel
import com.example.lab3preliminaries.screen.StartScreen

sealed class Screen(val route: String){
    object StartScreen: Screen(route = "start_screen")
    object QuestionScreen: Screen(route = "question_screen")
    object CompletedScreen: Screen(route = "completed_screen")
    object CancelScreen: Screen(route = "cancel_screen")
}

@Composable
fun NavGraph(startDestination: String = Screen.StartScreen.route){
    val navController = rememberNavController()
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController = navController,
                quizViewModel = quizViewModel
            )
        }

        composable(route = Screen.QuestionScreen.route) {
            QuestionScreen(
                navController = navController,
                quizViewModel = quizViewModel
            )
        }

        composable(route = Screen.CompletedScreen.route) {
            CompletedScreen(
                navController = navController,
                quizViewModel = quizViewModel
            )
        }
    }
}