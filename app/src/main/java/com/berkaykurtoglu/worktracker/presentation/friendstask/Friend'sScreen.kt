package com.berkaykurtoglu.worktracker.presentation.friendstask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.friendstask.screen.components.FilterCategorie
import com.berkaykurtoglu.worktracker.presentation.friendstask.screen.components.FilterChips
import com.berkaykurtoglu.worktracker.presentation.friendstask.screen.components.FriendsTasksLazyColumn
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FriendsScreen(
    viewModel: FriendsTaskViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by remember {
        viewModel.state
    }

    val filterList = listOf(
        FilterCategorie.UnMarkedChip,FilterCategorie.MarkedChip
    )

    val chipIndex = remember {
        viewModel.chipIndex
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            if (chipIndex.value == FilterCategorie.MarkedChip ) viewModel.getFriendsMarkedTasks()
            else viewModel.getFriendsUnmarkedTasks()
        },
        refreshThreshold = 44.dp
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                pullRefreshState, true
            ),
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            FilterChips(filterList,chipIndex)


            if (state.error.isNotBlank()){
                TODO()
            }else{
                //Show lazy column
                if(state.tasks.isEmpty()){

                }else{
                    FriendsTasksLazyColumn(list = state.tasks,navController)
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