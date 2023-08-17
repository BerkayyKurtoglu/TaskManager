package com.berkaykurtoglu.worktracker.presentation.noteinput.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.noteinput.NoteInputViewModel
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component.ActualScreen
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component.AddButton
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component.ColorPicker
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component.TopBarComponent
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component.datePicker
import com.berkaykurtoglu.worktracker.presentation.theme.DefaultColor
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NoteInputScreen(
    navController: NavController,
    viewModel: NoteInputViewModel = hiltViewModel(),
    signOutNavController: NavController
) {

    val state = viewModel.state
    val calendarState = rememberUseCaseState(

    )

    val titleText = remember {
        mutableStateOf("")
    }

    val bodyText = remember {
        mutableStateOf("")
    }

    val date = remember {
        mutableStateOf("")
    }

    val backGroundColor = remember {
        mutableStateOf(DefaultColor)
    }

    val colorPickerState = remember {
        mutableStateOf(false)
    }

    date.value = datePicker(calendarState)


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarComponent(navController,calendarState,backGroundColor.value,colorPickerState,date.value) }

    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(backGroundColor.value)
        ) {

            if (state.value.errorMsg.isNotBlank()){
                LaunchedEffect(key1 = Unit){
                    navController.popBackStack()
                }
                Toast.makeText(navController.context,state.value.errorMsg,Toast.LENGTH_LONG).show()
            }

            if (state.value.isSuccesfull){
                LaunchedEffect(key1 = Unit){
                   /*signOutNavController.navigate(
                       Screens.MainScreen.route+"{}"
                   ){
                       popUpTo(Screens.NoteInputScreen.route){inclusive=true}
                   }*/
                    navController.popBackStack()
                }
            }

            ColorPicker(colorPickerState = colorPickerState,backGroundColor)

            ActualScreen(
                titleText = titleText,
                bodyText = bodyText,
                modifier = Modifier.weight(1f)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
            ) {
                if (state.value.isLoading) CircularProgressIndicator()
                else {
                    AddButton(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        if (titleText.value.isNotBlank()) {
                            viewModel.addATask(
                                titleText.value, bodyText.value, date.value,
                                backGroundColor.value.toArgb().toString()
                            )
                            return@AddButton
                        }
                        Toast.makeText(
                            navController.context,
                            "Please, give a title to your task 😉",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


}