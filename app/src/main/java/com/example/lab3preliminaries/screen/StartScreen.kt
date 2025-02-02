package com.example.lab3preliminaries.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab3preliminaries.ui.theme.Lab3PreliminariesTheme

@Composable
fun StartScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = QuizViewModel()
){
    Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding ->
        var username by remember { mutableStateOf("") }
        Column (modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Extrovert/Introvert Test",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Enter your name to Start")
            UserInput(
                name = username,
                onNameChange = {
                    username = it
                }
            )
            Button(
                onClick = {
                    if (username.isNotEmpty()){
                        quizViewModel.setName(username)
                        navController.navigate("question_screen")
                        // Somehow pass username value to CompletedScreen
                    }
               },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Start")
            }
        }
    }
}

@Composable
fun UserInput(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text("Enter your Name") },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    Lab3PreliminariesTheme {
        val navController = rememberNavController()

        StartScreen(
            navController = navController
        )
    }
}