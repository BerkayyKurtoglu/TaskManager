package com.berkaykurtoglu.worktracker.presentation.yourtask

import com.berkaykurtoglu.worktracker.domain.model.Task

data class YourTaskState(
    val isLoading : Boolean = true,
    val error : String = "",
    val tasks : List<Task> = listOf(),
    val isDone : Boolean = false
)
