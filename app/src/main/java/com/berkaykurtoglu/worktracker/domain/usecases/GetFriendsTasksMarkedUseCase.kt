package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.FriendsTaskRepository
import javax.inject.Singleton

@Singleton
class GetFriendsTasksMarkedUseCase(
    private val friendsTasksRepository : FriendsTaskRepository
) {

    operator fun invoke() = friendsTasksRepository.getFriendsMarkedTasks()

}