package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.TaskDetailRepository
import javax.inject.Singleton

@Singleton
class MarkAsDoneUseCase(
    private val taskDetailRepository: TaskDetailRepository
) {

    operator fun invoke(
        documentId: String
    ) = taskDetailRepository.markAsDone(documentId)


}