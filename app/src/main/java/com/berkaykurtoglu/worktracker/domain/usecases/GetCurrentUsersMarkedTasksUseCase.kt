package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.YourTaskRepository

class GetCurrentUsersMarkedTasksUseCase(
    private val repository: YourTaskRepository
) {

    operator fun invoke(
        email : String
    ) = repository.getUsersMarkedTasks(email)

}