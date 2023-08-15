package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.SearchRepository
import com.berkaykurtoglu.worktracker.domain.model.Task
import javax.inject.Singleton

@Singleton
class SearchTasksUseCase(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(
        list: List<Task>,
        query : String
    ) = searchRepository.searchForTasks(list, query)


}