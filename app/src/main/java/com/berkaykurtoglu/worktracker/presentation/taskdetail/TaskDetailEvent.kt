package com.berkaykurtoglu.worktracker.presentation.taskdetail

import com.berkaykurtoglu.worktracker.domain.model.Comment

sealed class TaskDetailEvent{

    data class AddComment(
        val comment: Comment,
        val taskDocumentId : String
    ) : TaskDetailEvent()

}
