package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class SearchRepository (
    private val firebase : Firebase
){

    fun searchForTasks(
        list : List<Task>,
        query : String
    ) : Flow<Resource<List<Task>>> = flow {

        emit(Resource.Loading())
        try {
            val filteredList = list.filter { it.title.contains(query,true) }
            emit(Resource.Success(filteredList))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }

    fun getTasksOnce(

    ) : Flow<Resource<List<Task>>> = flow {

        emit(Resource.Loading())
        try {
            val tasks = firebase.firestore.collection(Constants.TASK_COLLECTION)
                .get().await().toObjects(Task::class.java)
            emit(Resource.Success(tasks))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Could not take the tasks"))
        }

    }





}