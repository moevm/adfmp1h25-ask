package com.example.hotseat.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotseat.DifficultySelectionScreen
import com.example.hotseat.QuestionsListScreen
import com.example.hotseat.RatingsScreen
import com.example.hotseat.ui.home.HomeScreen
import com.example.hotseat.ui.questions.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = NavRoutes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                onNavigateToDifficulty = {
                    navController.navigate(NavRoutes.DIFFICULTY)
                },
                onNavigateToRound = {
                    navController.navigate(NavRoutes.ASK)
                }
            )
        }

        composable(NavRoutes.DIFFICULTY) {
            Scaffold { innerPadding ->
                DifficultySelectionScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackClick = { navController.popBackStack() },
                    onDifficultySelected = { difficulty ->
                        navController.navigate(NavRoutes.QUESTIONS_LIST)
                    }
                )
            }
        }

        composable(NavRoutes.QUESTIONS_LIST) {
            Scaffold { innerPadding ->
                QuestionsListScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable(NavRoutes.RATINGS) {
            Scaffold { innerPadding ->
                RatingsScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackClick = { navController.popBackStack() },
                    onResetClick = { /* Handle reset */ }
                )
            }
        }

        composable(NavRoutes.ASK) {
            AskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onStartGame = {
                    navController.navigate(NavRoutes.QUESTION) {
                        popUpTo(NavRoutes.ASK) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.QUESTION) {
            QuestionScreen(
                onAnswerSubmitted = {
                    navController.navigate(NavRoutes.GUESS_ANSWER) {
                        popUpTo(NavRoutes.QUESTION) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.GUESS_ANSWER) {
            GuessAnswerScreen(
                onAnswerSubmitted = {
                    navController.navigate(NavRoutes.PASS_TURN) {
                        popUpTo(NavRoutes.GUESS_ANSWER) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.PASS_TURN) {
            PassTurnScreen(
                onPassTurn = {
                    navController.navigate(NavRoutes.WINNER) {
                        popUpTo(NavRoutes.PASS_TURN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.WINNER) {
            WinnerScreen(
                onPlayAgain = {
                    navController.navigate(NavRoutes.ASK) {
                        popUpTo(NavRoutes.HOME)
                    }
                },
                onShowLeaderboard = {
                    navController.navigate(NavRoutes.RATINGS)
                },
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
} 