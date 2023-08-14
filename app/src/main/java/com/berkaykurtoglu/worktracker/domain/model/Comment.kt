package com.berkaykurtoglu.worktracker.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class Comment(
    var user : String = "",
    var body : String = "",
    var date : Timestamp = Timestamp.now(),
    @DocumentId
    var documentId: String = ""
){

    constructor() : this("","",date= Timestamp.now())


}
