package com.berkaykurtoglu.worktracker.presentation.noteinput

sealed class NoteInputEvent{

    data class AddATask(
        var title: String,
        var body: String,
        var deadLine: String,
        var backGround: String,
        var isPrivate : Boolean
    ) : NoteInputEvent()

}
