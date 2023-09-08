package com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.yourtask.YourTasksViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun YourTaskScreen(
    viewModel: YourTasksViewModel = hiltViewModel(),
    navController: NavController,
    isPrivateSelection : Boolean
) {

    val state by remember {
        viewModel.state
    }

    val chipIndex = remember {
        viewModel.chipIndex
    }

    val filterList = listOf<FilterCategorie>(
        FilterCategorie.UnMarkedChip, FilterCategorie.MarkedChip
    )


    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            if (chipIndex.value == FilterCategorie.MarkedChip) viewModel.getMarkedTasks()
            else viewModel.getUnmarkedTasks()
        },
        refreshThreshold = 44.dp
    )



    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            FilterChips(filterList,chipIndex,isPrivateSelection=isPrivateSelection)

            if (state.error.isNotBlank()){
                TODO()
            }else{
                //Show lazy column
                if(state.tasks.isEmpty() and !state.isLoading){
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "You do not have any task, start adding 😎",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }else{
                    Column {
                        //Spacer(modifier = Modifier.height(10.dp))
                        TasksLazyColumn(list = state.tasks,navController)
                    }
                }
            }
        }


        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )


    }



}