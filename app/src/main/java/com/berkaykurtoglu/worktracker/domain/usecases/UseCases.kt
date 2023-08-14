package com.berkaykurtoglu.worktracker.domain.usecases

data class UseCases(

    val signIn: SignInUseCase,
    val getCurrentUser : CurrentUserUseCase,
    val signOutUseCase: SignOutUseCase,
    val addATask: AddATask,
    val getCurrentUsersTasks: GetCurrentUsersTasksUseCase,
    val getFriendsTasksUseCase: GetFriendsTasksUseCase,
    val getTaskDetailUseCase: GetTaskDetailUseCase,
    val addACommentUseCase: AddACommentUseCase

)
