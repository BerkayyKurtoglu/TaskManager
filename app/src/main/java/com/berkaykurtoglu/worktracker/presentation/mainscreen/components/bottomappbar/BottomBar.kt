package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.bottomappbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainEvent
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.FloatingAction
import com.berkaykurtoglu.worktracker.util.Category
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Screens
import com.google.firebase.firestore.auth.User

@Composable
fun BottomBarScreen (
    viewModel: MainViewModel,
    signOutNavController: NavController,
    navController : NavController,
    category: Category
) {

    var haptic = LocalHapticFeedback.current

    var isVisible by remember {
        mutableStateOf(true)
    }
    var coroutineScope = rememberCoroutineScope()

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
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn()+ slideInHorizontally(),
                exit = fadeOut()+ slideOutHorizontally()
            ) {
                FloatingAction {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    navController.navigate(Screens.NoteInputScreen.route)
                }
            }

        }

    )

}

