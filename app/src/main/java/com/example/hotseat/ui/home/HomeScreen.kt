package com.example.hotseat.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.components.CommonButton

@Composable
fun HomeScreen(
    onNavigateToRatings: () -> Unit,
    onNavigateToDifficulty: () -> Unit,
    onNavigateToPlay: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Задай вопрос другому",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        
        CommonButton(
            text = "Играть",
            onClick = onNavigateToPlay
        )

        Spacer(modifier = Modifier.height(16.dp))

        CommonButton(
            text = "Таблица лидеров",
            onClick = onNavigateToRatings
        )

        Spacer(modifier = Modifier.height(16.dp))

        CommonButton(
            text = "База вопросов",
            onClick = onNavigateToDifficulty
        )
    }
} 