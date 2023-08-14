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
class YourTaskRepository(
    private val firebase : Firebase
) {


    fun getUsersMarkedTasks(
        email : String
    ) = flow<Resource<List<Task>>> {
        emit(Resource.Loading())
        try {

            val taskList = firebase.firestore.collection(Constants.TASK_COLLECTION)
                .whereEqualTo("user",email).whereEqualTo("isMarked",true)
                .get().await().toObjects(Task::class.javaObjectType)
            emit(Resource.Success(taskList))

        }catch (e : FirebaseFirestoreException){
            println(e.localizedMessage)
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong try again"))
        }
    }

    fun getUsersUnmarkedTasks(
        email : String
    ) = flow<Resource<List<Task>>> {
        emit(Resource.Loading())
        try {

            val taskList = firebase.firestore.collection(Constants.TASK_COLLECTION)
                .whereEqualTo("user",email).whereEqualTo("isMarked",false)
                .get().await().toObjects(Task::class.javaObjectType)
            emit(Resource.Success(taskList))

        }catch (e : FirebaseFirestoreException){
            println(e.localizedMessage)
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong try again"))
        }
    }







}