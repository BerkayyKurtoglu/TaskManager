package com.berkaykurtoglu.worktracker.presentation.mainscreen

sealed class MainEvent(){

    object Profile : MainEvent()

    data class PrivateSelection(
        val isPrivate : Boolean
    ) : MainEvent()

}
