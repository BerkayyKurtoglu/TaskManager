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
class TaskItemViewRepository(
    private val firebase : Firebase
) {

    fun deleteTheTask(
        documentId : String
    ) = flow<Resource<Unit>> {

        emit(Resource.Loading())
        try {
            firebase.firestore.collection(Constants.TASK_COLLECTION)
                .document(documentId).delete().await()
            emit(Resource.Success(Unit))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Could not deleted"))
        }

    }


}