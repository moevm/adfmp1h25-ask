package com.example.hotseat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotseat.ui.collector.DifficultySelectionScreen as CollectorDifficultyScreen
import com.example.hotseat.ui.collector.QuestionsListScreen
import com.example.hotseat.ui.collector.RatingsScreen
import com.example.hotseat.ui.home.AboutScreen
import com.example.hotseat.ui.home.DifficultySelectionScreen as HomeDifficultyScreen
import com.example.hotseat.ui.home.HomeScreen
import com.example.hotseat.ui.home.PlayersSelectionScreen
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
                onNavigateToRatings = { navController.navigate(NavRoutes.RATINGS) },
                onNavigateToDifficulty = { navController.navigate(NavRoutes.DIFFICULTY) },
                onNavigateToPlay = { navController.navigate(NavRoutes.HOME_DIFFICULTY) },
                onNavigateToAbout = { navController.navigate(NavRoutes.ABOUT) }
            )
        }

        composable(NavRoutes.DIFFICULTY) {
            CollectorDifficultyScreen(
                onBackClick = { navController.popBackStack() },
                onDifficultySelected = { navController.navigate(NavRoutes.QUESTIONS_LIST) }
            )
        }

        composable(NavRoutes.QUESTIONS_LIST) {
            QuestionsListScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.HOME_DIFFICULTY) {
            HomeDifficultyScreen(
                onBackClick = { navController.popBackStack() },
                onDifficultySelected = { difficulty -> navController.navigate(NavRoutes.PLAYERS_SELECTION)
                }
            )
        }

        composable(NavRoutes.PLAYERS_SELECTION) {
            PlayersSelectionScreen(
                onBackClick = { navController.popBackStack() },
                onPlayClick = { players ->
                    navController.navigate(NavRoutes.ASK)
                }
            )
        }

        composable(NavRoutes.ABOUT) {
            AboutScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.RATINGS) {
            RatingsScreen(
                onBackClick = { navController.popBackStack() },
                onResetClick = { /* Handle reset */ }
            )
        }

        composable(NavRoutes.ASK) {
            AskScreen(
                onNavigateBack = { navController.popBackStack() },
                onStartGame = { navController.navigate(NavRoutes.QUESTION) }
            )
        }

        composable(NavRoutes.QUESTION) {
            QuestionScreen(
                onAnswerSubmitted = { navController.navigate(NavRoutes.GUESS_ANSWER) }
            )
        }

        composable(NavRoutes.GUESS_ANSWER) {
            GuessAnswerScreen(
                onAnswerSubmitted = { navController.navigate(NavRoutes.PASS_TURN) }
            )
        }

        composable(NavRoutes.PASS_TURN) {
            PassTurnScreen(
                onPassTurn = { navController.navigate(NavRoutes.WINNER) }
            )
        }

        composable(NavRoutes.WINNER) {
            WinnerScreen(
                onPlayAgain = { 
                    navController.popBackStack(NavRoutes.HOME, inclusive = false)
                    navController.navigate(NavRoutes.ASK)
                },
                onShowLeaderboard = { navController.navigate(NavRoutes.RATINGS) },
                onNavigateToHome = { navController.popBackStack(NavRoutes.HOME, inclusive = false) }
            )
        }
    }
} 