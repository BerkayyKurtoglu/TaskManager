package com.berkaykurtoglu.worktracker.presentation.mainscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.domain.model.User
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.bottomappbar.BottomBarScreen
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.profiledialog.ProfileDialog
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.table.TableRow
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterCategorie
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterChips
import com.berkaykurtoglu.worktracker.util.Category

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun ActualScreen(
    signOutNavController : NavController,
    viewModel : MainViewModel,
    inScreenNavController : NavController,
    category: MutableState<Category>
) {


    var dialogVisible = remember {
        mutableStateOf(false)
    }

    val dialogState by remember {
        viewModel.state
    }

    val isPrivateSelection = remember {
        mutableStateOf(false)
    }


    Scaffold(
        bottomBar = {
            BottomBarScreen(
                viewModel, signOutNavController = signOutNavController, navController = inScreenNavController, category = category.value,isPrivate = isPrivateSelection)
        }
    ){

        ProfileDialog(dialogVisible = dialogVisible, dialogState=dialogState)

            Column(Modifier.padding(bottom = it.calculateBottomPadding())) {
                AnimatedVisibility(visible = !viewModel.state.value.taskForOnceLoading ) {
                    Row() {
                        Spacer(modifier = Modifier
                            .height(0.dp)
                            .weight(1f))
                        SearchComponent(inScreenNavController)
                        Spacer(modifier = Modifier
                            .height(0.dp)
                            .weight(1f))
                    }
                }
                val index = TableRow(paddingValues = it, navController = inScreenNavController, isPrivate = isPrivateSelection.value)
                when(index){
                    0 ->{category.value = Category.YOUR_TASK}
                    1 -> {category.value = Category.FRIEND_TASK}
                }

            }
    }
}