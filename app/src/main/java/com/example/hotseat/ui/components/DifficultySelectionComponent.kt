package com.example.hotseat.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DifficultySelectionComponent(
    onDifficultySelected: (String) -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(80.dp))

        DifficultyButtonsGroup(
            onDifficultySelected = onDifficultySelected
        )
    }
}

@Composable
fun DifficultyButtonsGroup(
    onDifficultySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DifficultyButton(
                text = "Едва знакомы",
                color = Color(0xFF4CAF50),
                onClick = { onDifficultySelected("Едва знакомы") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DifficultyButton(
                text = "Общаемся",
                color = Color(0xFFFFA726),
                onClick = { onDifficultySelected("Общаемся") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DifficultyButton(
                text = "Заядлые кореша",
                color = Color(0xFFF44336),
                onClick = { onDifficultySelected("Заядлые кореша") }
            )
        }
    }
}

@Composable
fun DifficultyButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}