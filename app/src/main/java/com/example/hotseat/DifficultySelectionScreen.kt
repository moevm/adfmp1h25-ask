package com.example.hotseat

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.theme.HotseatTheme

@Composable
fun DifficultySelectionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDifficultySelected: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Title
        Text(
            text = "Выберите сложность",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(80.dp))

        // Difficulty buttons with bracket
        DifficultyButtonsGroup(
            onDifficultySelected = onDifficultySelected
        )

        Spacer(modifier = Modifier.weight(1f))

        // Back button
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .width(120.dp)
                .align(Alignment.Start)
                .padding(start = 16.dp, bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = "Назад",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DifficultyButtonsGroup(
    onDifficultySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Buttons column
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Easy difficulty - Green
            DifficultyButton(
                text = "Едва знакомы",
                color = Color(0xFF4CAF50),
                onClick = { onDifficultySelected("Едва знакомы") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Medium difficulty - Orange
            DifficultyButton(
                text = "Общаемся",
                color = Color(0xFFFFA726),
                onClick = { onDifficultySelected("Общаемся") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hard difficulty - Red
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

@Preview(showBackground = true)
@Composable
fun DifficultySelectionScreenPreview() {
    HotseatTheme {
        DifficultySelectionScreen(
            onBackClick = {},
            onDifficultySelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DifficultyButtonsGroupPreview() {
    HotseatTheme {
        DifficultyButtonsGroup(
            onDifficultySelected = {}
        )
    }
}