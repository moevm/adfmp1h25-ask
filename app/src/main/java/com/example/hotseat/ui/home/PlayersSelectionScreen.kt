package com.example.hotseat.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.theme.HotseatTheme
import com.example.hotseat.ui.components.ConfirmationDialog
import com.example.hotseat.ui.components.InputWithAddButton

@Composable
fun PlayersSelectionScreen(
    onBackClick: () -> Unit,
    onPlayClick: (List<String>) -> Unit
) {
    val players = remember { mutableStateListOf("Игрок 1", "Игрок 2", "Игрок 3") }
    var newPlayerName by remember { mutableStateOf("") }

    var playerToDelete by remember { mutableStateOf<String?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Добавьте игроков",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(players) { player ->
                PlayerItem(
                    name = player,
                    onDelete = {
                        playerToDelete = player
                        showDeleteDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        InputWithAddButton(
            value = newPlayerName,
            onValueChange = { newPlayerName = it },
            onAdd = {
                if (newPlayerName.isNotBlank()) {
                    players.add(newPlayerName)
                    newPlayerName = ""
                }
            },
            placeholder = "Введите имя нового игрока",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Назад")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onPlayClick(players) },
                enabled = players.size >= 2,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Играть")
            }
        }
    }

    if (showDeleteDialog && playerToDelete != null) {
        ConfirmationDialog(
            title = "Вы уверены, что хотите удалить этого игрока?",
            message = playerToDelete!!,
            confirmButtonText = "Удалить",
            dismissButtonText = "Отмена",
            onConfirm = {
                players.remove(playerToDelete)
                showDeleteDialog = false
                playerToDelete = null
            },
            onDismiss = {
                showDeleteDialog = false
                playerToDelete = null
            }
        )
    }
}

@Composable
fun PlayerItem(
    name: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить игрока",
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersSelectionScreenPreview() {
    HotseatTheme {
        PlayersSelectionScreen(
            onBackClick = {},
            onPlayClick = {}
        )
    }
}