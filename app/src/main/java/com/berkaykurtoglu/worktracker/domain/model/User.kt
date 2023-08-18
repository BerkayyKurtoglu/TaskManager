package com.berkaykurtoglu.worktracker.domain.model

data class User(

    val departmant : String = "",
    val email : String = "",
    val lastName : String = "",
    val name : String = ""

){

    constructor() : this("","","","")

}
