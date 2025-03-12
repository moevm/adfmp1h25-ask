package com.example.hotseat.ui.collector

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.theme.HotseatTheme
import com.example.hotseat.ui.components.ConfirmationDialog
import com.example.hotseat.ui.components.InputWithAddButton

@Composable
fun QuestionsListScreen(
    categoryName: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    // State for selected question and dialog visibility
    var selectedQuestion by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // State for delete confirmation dialog
    var questionToDelete by remember { mutableStateOf<String?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var deletionKey by remember { mutableStateOf<String?>(null) }

    // Helper function to truncate text for display in list
    fun truncateText(text: String, maxLength: Int = 30): String {
        return if (text.length <= maxLength) text
        else text.take(maxLength) + "..."
    }

    // Mutable state for questions with full text mapping
    val questionsMapState = remember {
        mutableStateOf(
            mutableMapOf(
                "Lorem ipsum dolor sit amet, cons..." to "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
                "Вопрос 1" to "Вопрос 1 полный текст",
                "Вопрос 2" to "Вопрос 2 полный текст",
                "Вопрос 3" to "Вопрос 3 полный текст",
                "Вопрос 4" to "Вопрос 4 полный текст",
                "Вопрос 5" to "Вопрос 5 полный текст",
                "Вопрос 6" to "Вопрос 6 полный текст",
                "Вопрос 7" to "Вопрос 7 полный текст"
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title with category name
        Text(
            text = "Список вопросов: $categoryName",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // Search field
        var searchText by remember { mutableStateOf("") }
        SearchField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = "Поиск вопроса",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // List of questions with delete buttons
        QuestionsList(
            questions = questionsMapState.value.keys.toList(),
            onDeleteQuestion = { question ->
                // Store both the display key and full text for deletion
                questionToDelete = questionsMapState.value[question]
                deletionKey = question
                showDeleteDialog = true
            },
            onQuestionClick = { question ->
                selectedQuestion = questionsMapState.value[question]
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Add new question field
        var newQuestion by remember { mutableStateOf("") }
        InputWithAddButton(
            value = newQuestion,
            onValueChange = { newQuestion = it },
            onAdd = {
                // Create a truncated version for display in the list
                val displayText = truncateText(newQuestion)

                // Add the new question to the map
                val updatedMap = questionsMapState.value.toMutableMap()
                updatedMap[displayText] = newQuestion
                questionsMapState.value = updatedMap

                // Clear the input field after adding
                newQuestion = ""
            },
            placeholder = "Введите свой вопрос",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Back button
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .width(120.dp)
                .align(Alignment.Start),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = "Назад",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

    // Question dialog
    if (showDialog && selectedQuestion != null) {
        QuestionDialog(
            text = selectedQuestion!!,
            onDismiss = { showDialog = false }
        )
    }

    // Delete confirmation dialog
    if (showDeleteDialog && questionToDelete != null && deletionKey != null) {
        ConfirmationDialog(
            title = "Вы уверены, что хотите удалить этот вопрос?",
            message = questionToDelete!!,
            confirmButtonText = "Удалить",
            dismissButtonText = "Отмена",
            onConfirm = {
                // Actually delete the question from the map
                val updatedMap = questionsMapState.value.toMutableMap()
                updatedMap.remove(deletionKey)
                questionsMapState.value = updatedMap

                // Reset dialog state
                showDeleteDialog = false
                questionToDelete = null
                deletionKey = null
            },
            onDismiss = {
                // Reset dialog state
                showDeleteDialog = false
                questionToDelete = null
                deletionKey = null
            }
        )
    }
}

@Composable
fun QuestionsList(
    questions: List<String>,
    onDeleteQuestion: (String) -> Unit,
    onQuestionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(questions) { question ->
            QuestionItem(
                text = question,
                onDelete = { onDeleteQuestion(question) },
                onClick = { onQuestionClick(question) }
            )
        }
    }
}

@Composable
fun QuestionItem(
    text: String,
    onDelete: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onDelete)
        )
    }
}

@Composable
fun QuestionDialog(
    text: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable(onClick = {})  // Prevent click propagation
                .padding(16.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Назад")
            }
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionsListScreenPreview() {
    HotseatTheme {
        QuestionsListScreen(
            categoryName = "Едва знакомы",
            onBackClick = {}
        )
    }
}