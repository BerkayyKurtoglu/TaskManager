package com.berkaykurtoglu.worktracker.presentation.noteinput

data class NoteInputState(

    val isSuccesfull : Boolean = false,
    val isLoading : Boolean = false,
    val errorMsg : String = ""

)
