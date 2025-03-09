package com.example.hotseat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.hotseat.ui.theme.HotseatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotseatTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "difficulty"
                ) {
                    composable("difficulty") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            DifficultySelectionScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = { /* Handle back button click */ },
                                onDifficultySelected = { difficulty ->
                                    navController.navigate("questionsList")
                                }
                            )
                        }
                    }
                    composable("questionsList") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            QuestionsListScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                    composable("ratings") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            RatingsScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = { navController.popBackStack() },
                                onResetClick = { /* Handle reset */ }
                            )
                        }
                    }
                }
            }
        }
    }
}