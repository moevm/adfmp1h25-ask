package com.example.hotseat.ui.questions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotseat.ui.components.CommonButton
import com.example.hotseat.ui.theme.Roboto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    onAnswerSubmitted: () -> Unit
) {
    var answer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ход 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            text = "Какой твой цвет любимый?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 150.dp)
        )

        Spacer(modifier = Modifier.height(200.dp))

        TextField(
            value = answer,
            onValueChange = { answer = it },
            modifier = Modifier
                .width(360.dp),
            placeholder = { 
                Text(
                    text = "Placeholder",
                    fontFamily = Roboto
                )
            },
            textStyle = LocalTextStyle.current.copy(fontFamily = Roboto),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            singleLine = true,
            shape = RoundedCornerShape(4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.padding(bottom = 100.dp)
        ) {
            CommonButton(
                text = "Ответить",
                onClick = onAnswerSubmitted
            )
        }
    }
} 