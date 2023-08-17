package com.berkaykurtoglu.worktracker.data

import com.berkaykurtoglu.worktracker.util.Resource
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class SignInRepository (
    private val firebase: Firebase
) {

    fun createUserWithEmailAndPassword(
        email: String,
        password: String
    )   = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        try {
            firebase.auth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(true))
        }catch (e : FirebaseAuthException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }

    fun getCurrentUser(

    ) = firebase.auth.currentUser


/*{


        firebase.auth.signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener {
            if (it.isSuccessful){
                println(it.result.user ?: "Yokiiiiii")
                listener.signInSuccess(
                    it.result.user!!
                )
            }else{
                println("error")
                listener.signInFailure(it.exception?.localizedMessage ?: "Error")
            }

        }


    }*/


}