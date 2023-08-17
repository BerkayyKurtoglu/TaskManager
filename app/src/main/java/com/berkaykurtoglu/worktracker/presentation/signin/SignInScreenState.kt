package com.berkaykurtoglu.worktracker.presentation.signin

data class SignInScreenState (

    var isLoading : Boolean = false,
    var error :  String = "",
    var user : String? = null,
    var isSignedIn :  Boolean = false

)