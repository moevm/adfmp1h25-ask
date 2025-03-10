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
fun AskScreen(
    onNavigateBack: () -> Unit,
    onStartGame: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Раунд 1",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto,
            modifier = Modifier.align(Alignment.Center)
        )
        
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            CommonButton(
                text = "Начать",
                onClick = onStartGame
            )
        }
    }
} 