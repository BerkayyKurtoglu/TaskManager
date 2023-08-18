package com.berkaykurtoglu.worktracker.presentation.mainscreen

import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.domain.model.User

data class MainScreenState (

    val isLoading : Boolean = false,
    val task : List<Task> = listOf(),
    val error : String = "",
    val taskForOnceLoading : Boolean = true,
    val taskForOnceList: List<Task> = listOf(),
    val dialogIsLoading : Boolean = true,
    val dialogError : String = "",
    val dialogUser : User = User()

)