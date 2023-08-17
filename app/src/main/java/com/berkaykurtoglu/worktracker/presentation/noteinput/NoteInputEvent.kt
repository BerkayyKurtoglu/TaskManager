package com.berkaykurtoglu.worktracker.presentation.noteinput

sealed class NoteInputEvent{

    data class addATask(
        var title: String,
        var body: String,
        var deadLine: String,
        var backGround: String,
    ) : NoteInputEvent()

}
