package com.berkaykurtoglu.worktracker.presentation.mainscreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.util.Screens
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchComponent(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val state by remember {
        mainViewModel.state
    }

    var query by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    var scope = rememberCoroutineScope()
    var job : Job? = null

    if (!isActive) {
        mainViewModel.clearTheList()
        query = ""
    }
    if (query.isBlank()) mainViewModel.clearTheList()

    SearchBar(
        query = query,
        onQueryChange = {
                        query = it
            job?.cancel()
            job = scope.launch {
                delay(500)
                mainViewModel.filterTasks(state.taskForOnceList,query)
            }
        } ,
        onSearch = {
            if (!state.taskForOnceLoading){
                mainViewModel.filterTasks(state.taskForOnceList,query)
            }
        } ,
        active = isActive ,
        onActiveChange = {
                         isActive = it
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
        },
        placeholder = { Text(text = "Search")},
        trailingIcon = {
            if (isActive){
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription ="",
                    modifier = Modifier.clickable {
                        if (query.isNotBlank()){
                            query = ""
                        }else{
                            isActive = false
                        }
                    }
                )
            }
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ){

            items(state.task){
                Text(
                    text = it.title,
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(5.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screens.TaskDetailScreen.route+it.documentId
                            )
                        }
                )
            }

        }

    }


}