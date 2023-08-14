package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ActualScreen(
    titleText : MutableState<String>,
    bodyText : MutableState<String>,
    modifier: Modifier = Modifier
) {

    TextField(
        value = titleText.value,
        onValueChange ={
            titleText.value = it
        },
        placeholder = {
            Text(
                text = "Title...",
                fontSize = 25.sp,
                color = Color(0xFF313131)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        )

    )

    TextField(
        value = bodyText.value,
        onValueChange ={
            bodyText.value = it
        },
        placeholder = {
            Text(
                text = "Tap to typing...",
                fontSize = 15.sp,
                color = Color(0xFF313131),
                textAlign = TextAlign.End
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences
        )

    )

}