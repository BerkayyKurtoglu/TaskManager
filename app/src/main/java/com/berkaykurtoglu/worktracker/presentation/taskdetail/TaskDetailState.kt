package com.berkaykurtoglu.worktracker.presentation.taskdetail

import com.berkaykurtoglu.worktracker.domain.model.Task

data class TaskDetailState (

    val isLoading : Boolean = true,
    val error : String = "",
    val task : Task = Task(),
    val isCommentUploaded : Boolean = false

)