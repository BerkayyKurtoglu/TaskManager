package com.berkaykurtoglu.worktracker.presentation.search

import com.berkaykurtoglu.worktracker.domain.model.Task

data class SearchScreenState(

    val isLoading : Boolean = false,
    val task : List<Task> = listOf(),
    val error : String = "",
    val taskForOnceLoading : Boolean = true,
    val taskForOnceList: List<Task> = listOf()

)
