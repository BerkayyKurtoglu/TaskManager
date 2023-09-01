package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.domain.model.User
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class MainRepository(
    private val firebase : Firebase
) {

    fun signOutAUser(){
        firebase.auth.signOut()
    }

    fun getCurrentUser() :String? = firebase.auth.currentUser?.email

    fun getUsersInfo(
        email : String
    ) : Flow<Resource<User?>> = flow {

        emit(Resource.Loading())
        try {
            val user =
                firebase.firestore.collection(Constants.USER_COLLECTION).document(email)
                .get().await().toObject(User::class.java)
            emit(Resource.Success(user))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }

    }

    fun searchForTasks(
        list : List<com.berkaykurtoglu.worktracker.domain.model.Task>,
        query : String
    ) : Flow<Resource<List<com.berkaykurtoglu.worktracker.domain.model.Task>>> = flow {

        emit(Resource.Loading())
        try {
            val filteredList =
                list.filter { it.title.contains(query,true) }
            emit(Resource.Success(filteredList))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }

    fun getTasksOnce(

    ) : Flow<Resource<List<com.berkaykurtoglu.worktracker.domain.model.Task>>> = flow {

        emit(Resource.Loading())
        try {
            val tasks =
                firebase.firestore.collection(Constants.TASK_COLLECTION)
                .get().await().toObjects(com.berkaykurtoglu.worktracker.domain.model.Task::class.java)
            emit(Resource.Success(tasks))
        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Could not take the tasks"))
        }

    }


}