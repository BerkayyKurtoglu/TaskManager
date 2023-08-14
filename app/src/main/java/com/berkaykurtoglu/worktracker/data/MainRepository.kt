package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.model.Document
import com.google.firebase.ktx.Firebase
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

    fun getUsersTasks(
        email : String?
    ) = flow<Resource<List<com.berkaykurtoglu.worktracker.domain.model.Task>>> {

        emit(Resource.Loading())
        try {
            val tasks = firebase.firestore.collection(Constants.TASK_COLLECTION)
                .whereEqualTo("user",email).get().await().toObjects(com.berkaykurtoglu.worktracker.domain.model.Task::class.java)
            emit(Resource.Success(tasks))

        }catch (e : FirebaseFirestoreException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }

    }



}