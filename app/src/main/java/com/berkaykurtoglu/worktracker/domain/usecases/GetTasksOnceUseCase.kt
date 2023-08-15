package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.SearchRepository
import javax.inject.Singleton

@Singleton
class GetTasksOnceUseCase(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(

    ) = searchRepository.getTasksOnce()


}