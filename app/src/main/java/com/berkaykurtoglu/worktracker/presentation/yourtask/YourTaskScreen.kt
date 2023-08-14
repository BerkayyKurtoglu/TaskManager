package com.berkaykurtoglu.worktracker.presentation.yourtask.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.yourtask.YourTasksViewModel
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.TasksLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun YourTaskScreen(
    viewModel: YourTasksViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by remember {
        viewModel.state
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.getTasks() },
        refreshThreshold = 44.dp
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {

        if (state.error.isNotBlank()){
            TODO()
        }else{
            //Show lazy column
            if(state.tasks.isEmpty()){

            }else{
                TasksLazyColumn(list = state.tasks,navController)
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )


    }



}