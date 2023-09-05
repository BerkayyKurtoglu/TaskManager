package com.berkaykurtoglu.worktracker.presentation.TaskItemView

data class TaskItemViewState(
    var isLoading : Boolean = false,
    var error : String = "",
    var isCompleted : Boolean = false
)
