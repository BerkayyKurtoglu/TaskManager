package com.berkaykurtoglu.worktracker.domain.model

import com.google.firebase.firestore.DocumentId

data class Task(

    var title : String = "",
    var body : String = "",
    var deadLine : String = "",
    var backGround : String = "",
    var comments : List<Comment> = listOf(),
    var user : String = "",
    @DocumentId
    var documentId : String = "",
    @field:JvmField
    var isMarked : Boolean = false

) {

    constructor() : this("", "", "", "", listOf(), "",isMarked = false)

}
