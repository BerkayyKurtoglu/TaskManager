package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class NoteInputRepository(
    private val firebase : Firebase
){

    fun addATask(
        task : Task
    ) : kotlinx.coroutines.flow.Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            firebase.firestore.collection(Constants.TASK_COLLECTION).document().set(
                task
            ).await()
            emit(Resource.Success(data = Unit))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error occured"))
        }
    }


}

