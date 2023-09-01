package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.FriendsTaskRepository

class GetFriendsUnmarkedTasksUseCase(
    private val friendsTaskRepository: FriendsTaskRepository
) {

    operator fun invoke(email : String) = friendsTaskRepository.getFriendsUnmarkedTasks(email)


}