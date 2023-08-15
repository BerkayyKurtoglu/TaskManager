package com.berkaykurtoglu.worktracker.util

sealed class Screens(var route: String){

    object TabScreen : Screens("TabScreen")
    object NoteInputScreen : Screens("NoteInput")
    object TaskDetailScreen : Screens("TaskDetail")
    object LoginScreen : Screens("Login")
    object MainScreen : Screens("Main/")
    object SearchScreen : Screens("Search")

}
