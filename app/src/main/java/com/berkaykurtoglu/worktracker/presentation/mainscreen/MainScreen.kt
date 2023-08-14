package com.berkaykurtoglu.worktracker.presentation.mainscreen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkaykurtoglu.worktracker.util.Category
import com.berkaykurtoglu.worktracker.util.Screens
import com.berkaykurtoglu.worktracker.presentation.mainscreen.screen.component.table.TableRow
import com.berkaykurtoglu.worktracker.presentation.mainscreen.screen.component.TopBar
import com.berkaykurtoglu.worktracker.presentation.mainscreen.components.bottomappbar.BottomBarScreen
import com.berkaykurtoglu.worktracker.presentation.noteinput.screen.NoteInputScreen
import com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.TaskDetailScreen
import com.berkaykurtoglu.worktracker.util.Constants

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    signOutNavController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {

    var category by remember {
        mutableStateOf(Category.YOUR_TASK)
    }
    val navController = rememberNavController()

    val appBarState = rememberTopAppBarState()


    NavHost(navController = navController, startDestination = Screens.TabScreen.route) {

        composable(Screens.TabScreen.route){
            Scaffold(
                topBar =  {
                    TopBar(
                        modifier = Modifier
                            .shadow(0.dp),
                    )
                },
                bottomBar = {
                    BottomBarScreen(viewModel, signOutNavController = signOutNavController,navController,category)
                },
            ){
                val index = TableRow(paddingValues = it, navController = navController)
                when(index){
                    0 ->{category = Category.YOUR_TASK}
                    1 -> {category = Category.FRIEND_TASK}
                }
            }
        }

        composable(Screens.NoteInputScreen.route){
            NoteInputScreen(navController, signOutNavController = signOutNavController)
        }

        composable(
            Screens.TaskDetailScreen.route+"{${Constants.DOCUMENT_ID_ARG}}",
            arguments = listOf(
                navArgument(Constants.DOCUMENT_ID_ARG){type = NavType.StringType}
            )
        ){
            TaskDetailScreen(
                documentId = it.arguments?.getString(Constants.DOCUMENT_ID_ARG) ?: "",
                navController
            )
        }

    }

}