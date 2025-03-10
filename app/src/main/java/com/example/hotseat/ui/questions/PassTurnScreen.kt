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
import com.example.hotseat.ui.theme.Red
import com.example.hotseat.ui.theme.Roboto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassTurnScreen(
    onPassTurn: () -> Unit
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
            text = "Что ответит игрок Саша",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 80.dp)
        )

        Text(
            text = "Какой твой цвет любимый?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(150.dp))

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

        Button(
            onClick = onPassTurn,
            modifier = Modifier
                .width(360.dp)
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp
            )
        ) {
            Text(
                text = "Передать ход игроку \"Александр\"",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Roboto
            )
        }

        Text(
            text = "Осталось: 1:52",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp)
        )
    }
} 