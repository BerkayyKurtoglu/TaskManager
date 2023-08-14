package com.berkaykurtoglu.worktracker.presentation.signin.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.berkaykurtoglu.worktracker.presentation.signin.SignInScreenViewModel
import com.berkaykurtoglu.worktracker.presentation.theme.TextFieldBackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualScreen(
    buttonText : String,
    viewModel : SignInScreenViewModel
) {

    val state by remember {
        viewModel.state
    }

    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = emailText ,
            onValueChange = {
                emailText = it
            },
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription ="" )
            },
            trailingIcon = {
                           //val icon = if (passwordVisible) Icons.Outlined. else Icons.Outlined.VisibilityOff
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.5.dp, RoundedCornerShape(13.dp)),
            shape = RoundedCornerShape(13.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackGroundColor,
                unfocusedContainerColor = TextFieldBackGroundColor,
                disabledContainerColor = TextFieldBackGroundColor,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )

            )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = passwordText ,
            onValueChange = {
                passwordText = it
            },
            placeholder = {
                Text(text = "Password")
            },
            leadingIcon = {
                 Icon(imageVector = Icons.Outlined.Lock, contentDescription ="" )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.5.dp, RoundedCornerShape(13.dp)),
            shape = RoundedCornerShape(13.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackGroundColor,
                unfocusedContainerColor = TextFieldBackGroundColor,
                disabledContainerColor = TextFieldBackGroundColor,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        ElevatedButton(
            onClick = {
                if (emailText.isNotBlank() && passwordText.isNotBlank()) {
                    viewModel.signIn(emailText, passwordText)
                }
            },
        ) {
            Text(text = buttonText)
        }
    }

}