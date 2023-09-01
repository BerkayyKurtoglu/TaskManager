package com.berkaykurtoglu.worktracker.presentation.signin.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.signin.SignInScreenViewModel
import com.berkaykurtoglu.worktracker.presentation.signin.screen.components.ActualScreen
import com.berkaykurtoglu.worktracker.presentation.signin.screen.components.ErrorScreen
import com.berkaykurtoglu.worktracker.util.Screens

@Composable
fun LogInScreen (
    navController: NavController,
    viewModel : SignInScreenViewModel = hiltViewModel()
) {

    val result by remember {
        viewModel.state
    }

    var buttonText by remember {
        mutableStateOf("Sign In")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {

        if (result.isSignedIn) {
            LaunchedEffect(key1 = Unit){
                navController.navigate(Screens.MainScreen.route+"{}")
            }
        }

        ActualScreen(buttonText = buttonText, viewModel = viewModel)

        AnimatedVisibility(visible = result.isLoading) {
            CircularProgressIndicator(
                strokeWidth = 4.dp,
            )
        }
        AnimatedVisibility(visible = result.error.isNotBlank()) {
            ErrorScreen(result.error)
            buttonText = "Retry"
        }

    }



}