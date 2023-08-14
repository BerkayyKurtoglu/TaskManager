package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.presentation.mainscreen.screen.component.bottomappbar.FloatingAction
import com.berkaykurtoglu.worktracker.util.Category
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Screens

//Commit test

@Composable
fun BottomBarScreen (
    viewModel: MainViewModel,
    signOutNavController: NavController,
    navController : NavController,
    category: Category
) {

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

            IconButton(onClick = {


            }
            ) {
                Icon(Icons.Outlined.Search,"Exit")
            }

        },
        floatingActionButton = {
            if (category == Category.YOUR_TASK){
                FloatingAction {
                    //Your task category
                    navController.navigate(Screens.NoteInputScreen.route)
                }
            } },
    )

}

