package com.berkaykurtoglu.worktracker.presentation.TaskItemView

sealed class TaskItemViewEvent{

    data class Delete(
        val documentId : String
    ) : TaskItemViewEvent()


}
