package com.berkaykurtoglu.worktracker.presentation.friendstask

import com.berkaykurtoglu.worktracker.domain.model.Task

data class FriendsTaskState(
    val isLoading : Boolean = true,
    val error : String = "",
    val tasks : List<Task> = listOf()
)