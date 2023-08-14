package com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.TaskItemView.TaskItemView
import com.berkaykurtoglu.worktracker.util.Category

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksLazyColumn(
    list : List<com.berkaykurtoglu.worktracker.domain.model.Task>,
    navController: NavController
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing =15.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
    ){

        items(list){
            //Show the items
            TaskItemView(task = it,Category.YOUR_TASK,navController)
        }

    }

}