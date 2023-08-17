package com.berkaykurtoglu.worktracker.presentation.friendstask.screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkaykurtoglu.worktracker.presentation.friendstask.FriendsTaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChips(
    filterList: List<FilterCategorie>,
    selectedItem : MutableState<FilterCategorie>,
    viewModel : FriendsTaskViewModel = hiltViewModel()
) {



    LazyRow(
        contentPadding = PaddingValues(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        items(filterList){

            FilterChip(
                modifier = Modifier.padding(end =10.dp).animateContentSize { initialValue, targetValue ->},
                selected = it == selectedItem.value,
                onClick = {
                    selectedItem.value = it
                    when(it){
                        is FilterCategorie.UnMarkedChip ->{
                            println("unmarked")
                            viewModel.getFriendsUnmarkedTasks()
                        }
                        is FilterCategorie.MarkedChip ->{
                            println("marked")
                            viewModel.getFriendsMarkedTasks()
                        }
                    }
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderWidth = 0.1.dp
                ),
                label = {
                    Text(text = it.title)
                },
                leadingIcon = {
                    if (it == selectedItem.value) Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = ""
                    )
                }
            )

        }

    }



}