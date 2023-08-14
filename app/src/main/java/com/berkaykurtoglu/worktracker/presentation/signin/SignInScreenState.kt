package com.berkaykurtoglu.worktracker.presentation.signin

import com.google.firebase.auth.FirebaseUser

data class SignInScreenState (

    var isLoading : Boolean = false,
    var error :  String = "",
    var user : String? = null,
    var isSignedIn :  Boolean = false

)