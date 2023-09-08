package com.berkaykurtoglu.worktracker.presentation.yourtask

sealed class YourTaskEvent{

    data class PrivateFilterSelection(
        val isPrivate : Boolean = false,
        val isDone : Boolean = true
    ) : YourTaskEvent()

    object DoneSelection : YourTaskEvent()

    object UnDoneSelection : YourTaskEvent()


}