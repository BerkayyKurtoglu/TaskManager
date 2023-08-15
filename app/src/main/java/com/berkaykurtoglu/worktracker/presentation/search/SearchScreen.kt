package com.berkaykurtoglu.worktracker.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.search.lazycolumn.LazyTaskColumn
import com.berkaykurtoglu.worktracker.presentation.theme.TextFieldBackGroundColor
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    mainNavController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    val state by remember {
        viewModel.state
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    var job : Job? = null

    Surface(
        color = Color(0xFFFFFFFF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 20.dp, top = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        mainNavController.popBackStack()
                    }
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                }

                AnimatedVisibility(visible = !state.taskForOnceLoading) {
                    OutlinedTextField(
                        value = searchText ,
                        onValueChange = {
                            searchText = it
                            if (!state.taskForOnceLoading){
                                viewModel.filterTasks(
                                    state.taskForOnceList,searchText
                                )
                                println(state.task)
                            }
                        },
                        placeholder = { Text(text = "Search For Titles")},
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = TextFieldBackGroundColor,
                            unfocusedContainerColor = TextFieldBackGroundColor
                        ),
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(50.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (!state.taskForOnceLoading){
                                    viewModel.filterTasks(
                                        state.taskForOnceList,searchText
                                    )
                                    println(state.task)
                                }
                            }
                        ),
                        singleLine = true
                    )
                }
            }

            if (state.taskForOnceLoading) CircularProgressIndicator()
            if (state.isLoading) CircularProgressIndicator()
            if (state.task.isNotEmpty()){
                //Show the screen
                LazyTaskColumn(state.task,mainNavController)
            }

        }
    }

}