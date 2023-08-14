package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.YourTaskRepository
import javax.inject.Singleton

@Singleton
class GetCurrentUsersUnmarkedTasksUseCase (
    private val yourTaskRepository: YourTaskRepository
){

    operator fun invoke(
        email : String
    ) = yourTaskRepository.getUsersUnmarkedTasks(email)



}