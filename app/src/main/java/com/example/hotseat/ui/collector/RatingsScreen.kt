package com.example.hotseat.ui.collector

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.theme.HotseatTheme
import com.example.hotseat.ui.components.ConfirmationDialog

data class PlayerRating(
    val name: String,
    val wins: Int
)

@Composable
fun RatingsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onResetClick: () -> Unit
) {
    // State for reset confirmation dialog
    var showResetDialog by remember { mutableStateOf(false) }

    // Player ratings table with mutable state
    val playersState = remember {
        mutableStateOf(
            listOf(
                PlayerRating("Игрок 1", 10),
                PlayerRating("Игрок 2", 9),
                PlayerRating("Игрок 3", 8),
                PlayerRating("Игрок 4", 7),
                PlayerRating("Игрок 5", 6),
                PlayerRating("Игрок 6", 5),
                PlayerRating("Игрок 7", 4),
                PlayerRating("Игрок 8", 3)
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Рейтинг",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // Player ratings table
        RatingsTable(
            players = playersState.value,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text(
                    text = "Назад",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            // Reset button - now shows confirmation dialog
            Button(
                onClick = { showResetDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text(
                    text = "Сбросить",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }

    // Reset confirmation dialog
    if (showResetDialog) {
        ConfirmationDialog(
            title = "Вы уверены, что хотите сбросить таблицу лидеров?",
            message = "Это действие нельзя отменить.",
            confirmButtonText = "Сбросить",
            dismissButtonText = "Отмена",
            onConfirm = {
                // Actually reset the leaderboard by setting it to an empty list
                playersState.value = emptyList()
                onResetClick()
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false }
        )
    }
}

@Composable
fun RatingsTable(
    players: List<PlayerRating>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        // Table header
        RatingsTableRow(
            leftText = "Игрок",
            rightText = "Количество побед",
            isHeader = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Table content
        LazyColumn {
            items(players) { player ->
                RatingsTableRow(
                    leftText = player.name,
                    rightText = player.wins.toString(),
                    isHeader = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun RatingsTableRow(
    leftText: String,
    rightText: String,
    isHeader: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(1.dp, Color.Black)
    ) {
        // Left column (player name)
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Black)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = leftText,
                fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
            )
        }

        // Right column (win count)
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Black)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = rightText,
                fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingsScreenPreview() {
    HotseatTheme {
        RatingsScreen(
            onBackClick = {},
            onResetClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingsTablePreview() {
    HotseatTheme {
        RatingsTable(
            players = listOf(
                PlayerRating("Игрок 1", 10),
                PlayerRating("Игрок 2", 9),
                PlayerRating("Игрок 3", 8)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingsTableRowPreview() {
    HotseatTheme {
        Column {
            RatingsTableRow(
                leftText = "Игрок",
                rightText = "Количество побед",
                isHeader = true
            )
            RatingsTableRow(
                leftText = "Игрок 1",
                rightText = "10",
                isHeader = false
            )
        }
    }
}