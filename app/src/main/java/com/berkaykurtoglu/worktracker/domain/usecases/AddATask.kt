package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.NoteInputRepository
import com.berkaykurtoglu.worktracker.domain.model.Task
import javax.inject.Singleton

@Singleton
class AddATask(
    private val noteInputRepository: NoteInputRepository
) {

    operator fun invoke(
        task : Task
    ) = noteInputRepository.addATask(task = task)


}