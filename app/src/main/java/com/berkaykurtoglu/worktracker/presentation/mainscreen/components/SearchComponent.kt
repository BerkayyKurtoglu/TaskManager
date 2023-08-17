package com.berkaykurtoglu.worktracker.presentation.mainscreen.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainViewModel
import com.berkaykurtoglu.worktracker.util.Screens
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class
)
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
    val scope = rememberCoroutineScope()
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
            if (it) mainViewModel.getTasksOnce()
        },
        leadingIcon = {
            if(!isActive)
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                )
            else Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable { isActive = false })
        },
        placeholder = { Text(text = "Search")},
        trailingIcon = {
            if (isActive){
                if (query.isNotBlank()){
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
        },
        colors = SearchBarDefaults.colors(
            dividerColor = Color(0xFFB6B6B6)
        )
    ) {

        LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ){

            items(state.task){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(
                                Screens.TaskDetailScreen.route + it.documentId
                            )
                        }
                        .padding(vertical = 9.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .background(Color(0xFFD5D5D5), CircleShape)
                            .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = it.title ,
                        modifier = Modifier
                            .animateItemPlacement(),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier
                        .height(0.dp)
                        .weight(1f))

                    Canvas(modifier = Modifier.size(6.dp)){
                        drawCircle(
                            color =
                            if (it.isMarked) Color(0xFF6BA854)
                            else Color(0xFFD37373)
                        )
                    }

                    Spacer(modifier = Modifier.width(7.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = "",
                        tint = Color(0xFF979797),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                query = it.title
                                job?.cancel()
                                job = scope.launch {
                                    delay(500)
                                    mainViewModel.filterTasks(state.taskForOnceList, query)
                                }

                            }
                    )
                }
            }
            item {
                if (query.isNotBlank()&& state.task.isNotEmpty()) {
                    Text(
                        text = "${state.task.size} result found..",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF7C7C7C)
                    )
                    Text(
                        text = "(Red Dot means those tasks are not marked, the greens are marked)",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF7C7C7C)
                    )
                }

            }

        }

    }


}