package com.berkaykurtoglu.worktracker.data.di

import com.berkaykurtoglu.worktracker.data.FriendsTaskRepository
import com.berkaykurtoglu.worktracker.data.MainRepository
import com.berkaykurtoglu.worktracker.data.NoteInputRepository
import com.berkaykurtoglu.worktracker.data.SignInRepository
import com.berkaykurtoglu.worktracker.data.TaskDetailRepository
import com.berkaykurtoglu.worktracker.data.YourTaskRepository
import com.berkaykurtoglu.worktracker.domain.usecases.AddACommentUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.AddATask
import com.berkaykurtoglu.worktracker.domain.usecases.CurrentUserUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.GetCurrentUsersMarkedTasksUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.GetCurrentUsersUnmarkedTasksUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.GetFriendsTasksUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.GetTaskDetailUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.MarkAsDoneUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.SignInUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.SignOutUseCase
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideFirebase(

    ) : Firebase = Firebase

    @Singleton
    @Provides
    fun provideSignInRepo(
        firebase : Firebase,
    ) : SignInRepository = SignInRepository(firebase)


    @Singleton
    @Provides
    fun provideMainRepository(
        firebase: Firebase
    ) : MainRepository = MainRepository(firebase)

    @Singleton
    @Provides
    fun provideYourTaskRepository(
        firebase: Firebase
    ) : YourTaskRepository = YourTaskRepository(firebase)

    @Singleton
    @Provides
    fun provideNoteInputRepository(
        firebase: Firebase
    ) : NoteInputRepository = NoteInputRepository(firebase)

    @Singleton
    @Provides
    fun provideFriendsTaskRepository(
        firebase: Firebase
    ) : FriendsTaskRepository = FriendsTaskRepository(firebase)

    @Singleton
    @Provides
    fun provideTaskDetailRepository(
        firebase: Firebase
    ) : TaskDetailRepository = TaskDetailRepository(firebase)

    @Singleton
    @Provides
    fun provideUseCases(
        signInRepository: SignInRepository,
        noteInputRepository: NoteInputRepository,
        yourTaskRepository: YourTaskRepository,
        friendsTaskRepository: FriendsTaskRepository,
        taskDetailRepository: TaskDetailRepository,
        firebase: Firebase
    ) : UseCases = UseCases(
        signIn = SignInUseCase(signInRepository),
        getCurrentUser = CurrentUserUseCase(firebase),
        signOutUseCase = SignOutUseCase(firebase),
        addATask = AddATask(noteInputRepository),
        getCurrentUsersMarkedTasks = GetCurrentUsersMarkedTasksUseCase(yourTaskRepository),
        getFriendsTasksUseCase = GetFriendsTasksUseCase(friendsTaskRepository),
        getTaskDetailUseCase = GetTaskDetailUseCase(taskDetailRepository),
        addACommentUseCase = AddACommentUseCase(taskDetailRepository),
        getCurrentUsersUnmarkedTasksUseCase = GetCurrentUsersUnmarkedTasksUseCase(yourTaskRepository),
        markAsDoneUseCase = MarkAsDoneUseCase(taskDetailRepository)

    )


}