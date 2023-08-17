package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.taskdetail.TaskDetailViewModel
import com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component.ActualScreen
import com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component.ErrorScreen
import com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component.TaskTopBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskDetailScreen(
    documentId : String,
    navController: NavController,
    viewModel : TaskDetailViewModel = hiltViewModel()
) {


    val state by remember {
        viewModel.state
    }

    if (state.isCommentUploaded)
        LaunchedEffect(key1 = Unit){
            viewModel.getTaskDetail(documentId)
        }

    //viewModel.getTaskDetail(documentId)

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.getTaskDetail(documentId) },
        refreshThreshold = 44.dp
    )

    Scaffold(
        topBar = { TaskTopBar(navController = navController)}
    ) {
        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopStart
        ){

            if (state.error.isNotBlank()){
                //Show the error message screen
                ErrorScreen(message = state.error, documentId = documentId)
            }
            if(!state.isLoading and state.error.isBlank()){
                ActualScreen(task = state.task,pullRefreshState, documentId = documentId, navController = navController)
            }

            PullRefreshIndicator(refreshing = state.isLoading, state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter))

        }
    }

}