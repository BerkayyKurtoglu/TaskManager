package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.domain.model.Comment
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Singleton

@Singleton
class TaskDetailRepository(
    private val firebase : Firebase
) {

    fun getTaskDetail(
        documentId: String
    ) : Flow<Resource<Task?>> = flow {

        emit(Resource.Loading())
        try {

            val task = firebase.firestore.collection(Constants.TASK_COLLECTION)
                .document(documentId).get().await().toObject(Task::class.java)
            emit(Resource.Success(task))

        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong try again"))
        }

    }

    fun addAComment(
        comment: Comment,
        documentId: String
    ) : Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {

            firebase.firestore.collection(Constants.TASK_COLLECTION).document(documentId)
                .update("comments",FieldValue.arrayUnion(comment)).await()
            emit(Resource.Success(Unit))

        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }

    }

    fun markAsDone(
        documentId: String
    ) : Flow<Resource<Unit>> = flow {

        emit(Resource.Loading())
        try {
            firebase.firestore.collection(Constants.TASK_COLLECTION)
                .document(documentId).update("isMarked",true).await()
            emit(Resource.Success(Unit))

        }catch (e : Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))
        }

    }






}