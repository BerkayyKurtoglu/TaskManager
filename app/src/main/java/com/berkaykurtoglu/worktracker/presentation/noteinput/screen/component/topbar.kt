package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.R
import com.maxkeppeker.sheets.core.models.base.UseCaseState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    navController: NavController,
    calendarState : UseCaseState,
    backGroundColor : Color,
    colorPickerState : MutableState<Boolean>,
    date : String,
    isPrivate : MutableState<Boolean>
) {

    TopAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
            .background(backGroundColor),
        title = {
                Text(text = date)
        },
        navigationIcon = {
            //Back Navigation
                         IconButton(onClick = { navController.popBackStack() }) {
                             Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                         }
        },
        actions = {

            //Private Button
            IconButton(onClick = {
                    isPrivate.value = !isPrivate.value
            }) {
                Icon(
                    imageVector = if (isPrivate.value) Icons.Outlined.Lock else Icons.Outlined.LockOpen ,
                    contentDescription = "Private Icon")
            }

            //Data Picker Button
            IconButton(onClick = {
                calendarState.show()
            }) {
                Icon(painter = painterResource(id = R.drawable.outline_more_time_24), contentDescription ="" )
            }

            //Background Picker Button
            IconButton(onClick = {
                colorPickerState.value = true
            }) {
                Icon(painter = painterResource(id = R.drawable.outline_color_lens_24), contentDescription ="" )
            }


        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backGroundColor
        )
    )

}