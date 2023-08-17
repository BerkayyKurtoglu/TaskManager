package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.MainRepository
import javax.inject.Singleton

@Singleton
class GetTasksOnceUseCase(
    private val mainRepository: MainRepository
) {

    operator fun invoke(

    ) = mainRepository.getTasksOnce()


}