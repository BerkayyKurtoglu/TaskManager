package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.MainRepository
import com.berkaykurtoglu.worktracker.domain.model.Task
import javax.inject.Singleton

@Singleton
class SearchTasksUseCase(
    private val mainRepository: MainRepository
) {

    operator fun invoke(
        list: List<Task>,
        query : String
    ) = mainRepository.searchForTasks(list, query)


}