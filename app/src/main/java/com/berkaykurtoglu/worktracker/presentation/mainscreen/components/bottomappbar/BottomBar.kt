package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.bottomappbar

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.FloatingAction
import com.berkaykurtoglu.worktracker.util.Category
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Screens

@Composable
fun BottomBarScreen (
    viewModel: MainViewModel,
    signOutNavController: NavController,
    navController : NavController,
    category: Category
) {

    var isVisible by remember {
        mutableStateOf(true)
    }

    isVisible = category == Category.YOUR_TASK

    BottomAppBar(
        actions ={

            IconButton(onClick = {
                viewModel.signOut()
                signOutNavController.navigate(
                    Screens.LoginScreen.route
                ){
                    this.popUpTo(Screens.MainScreen.route+ "{${Constants.EMAIL}}"){
                        this.inclusive = true
                    }
                }
            }
            ) {
                Icon(Icons.Outlined.ExitToApp,"Exit")
            }



        },
        floatingActionButton = {
            if (isVisible)
                FloatingAction {
                    //Your task category
                    navController.navigate(Screens.NoteInputScreen.route)
                } },

    )

}

