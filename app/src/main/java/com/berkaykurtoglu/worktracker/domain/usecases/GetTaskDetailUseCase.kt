package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.TaskDetailRepository
import javax.inject.Singleton

@Singleton
class GetTaskDetailUseCase(
    private val taskDetailRepository: TaskDetailRepository
) {

    operator fun invoke(
        documentId: String
    ) = taskDetailRepository.getTaskDetail(documentId)

}