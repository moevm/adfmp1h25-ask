package com.example.hotseat.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotseat.ui.components.CommonButton

@Composable
fun HomeScreen(
    onNavigateToDifficulty: () -> Unit,
    onNavigateToRound: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonButton(
            text = "Выбрать сложность",
            onClick = onNavigateToDifficulty
        )

        Spacer(modifier = Modifier.height(32.dp))

        CommonButton(
            text = "Начать раунд",
            onClick = onNavigateToRound
        )
    }
} 