package com.berkaykurtoglu.worktracker.domain.usecases

data class UseCases(

    val signIn: SignInUseCase,
    val getCurrentUser : CurrentUserUseCase,
    val signOutUseCase: SignOutUseCase,
    val addATask: AddATask,
    val getCurrentUsersMarkedTasks: GetCurrentUsersMarkedTasksUseCase,
    val getFriendsMarkedTasksUseCase: GetFriendsTasksMarkedUseCase,
    val getFriendsUnmarkedTasksUseCase : GetFriendsUnmarkedTasksUseCase,
    val getTaskDetailUseCase: GetTaskDetailUseCase,
    val addACommentUseCase: AddACommentUseCase,
    val getCurrentUsersUnmarkedTasksUseCase: GetCurrentUsersUnmarkedTasksUseCase,
    val markAsDoneUseCase: MarkAsDoneUseCase

)
