package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.TaskDetailRepository
import com.berkaykurtoglu.worktracker.domain.model.Comment
import javax.inject.Singleton

@Singleton
class AddACommentUseCase (
    private val taskDetailRepository: TaskDetailRepository
){

    operator fun invoke(
        comment: Comment,
        documentId: String
    ) = taskDetailRepository.addAComment(comment, documentId)


}