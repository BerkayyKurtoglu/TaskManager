package com.berkaykurtoglu.worktracker.presentation.mainscreen

sealed class MainEvent(){

    data object Profile : MainEvent()

    data class PrivateSelection(
        val isPrivate : Boolean
    ) : MainEvent()

}
