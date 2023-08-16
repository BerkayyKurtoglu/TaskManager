package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.TaskItemViewRepository
import javax.inject.Singleton

@Singleton
class DeleteATask(
    private val taskItemViewRepository : TaskItemViewRepository
) {

    operator fun invoke(
        documentId : String
    ) = taskItemViewRepository.deleteTheTask(documentId)


}