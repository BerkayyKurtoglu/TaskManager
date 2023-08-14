package com.berkaykurtoglu.worktracker.presentation.yourtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterCategorie
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterChips
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.TasksLazyColumn

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun YourTaskScreen(
    viewModel: YourTasksViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by remember {
        viewModel.state
    }

    val filterList = listOf<FilterCategorie>(
        FilterCategorie.UnMarkedChip, FilterCategorie.MarkedChip
    )

    val selectedItem = remember {
        mutableStateOf(filterList[0])
    }


    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            if (selectedItem.value == FilterCategorie.MarkedChip) viewModel.getMarkedTasks()
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
            FilterChips(filterList,selectedItem)

            if (state.error.isNotBlank()){
                TODO()
            }else{
                //Show lazy column
                if(state.tasks.isEmpty()){

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