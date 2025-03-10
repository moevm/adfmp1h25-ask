package com.example.hotseat.ui.questions

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.components.CommonButton
import com.example.hotseat.ui.theme.Roboto

@Composable
fun WinnerScreen(
    onPlayAgain: () -> Unit,
    onShowLeaderboard: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Победитель",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            text = "Игрок Александр",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Box(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            CommonButton(
                text = "Сыграть снова",
                onClick = onPlayAgain
            )
        }

        Box(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            CommonButton(
                text = "Таблица лидеров",
                onClick = onShowLeaderboard
            )
        }

        Box {
            CommonButton(
                text = "Главная страница",
                onClick = onNavigateToHome
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }
} 