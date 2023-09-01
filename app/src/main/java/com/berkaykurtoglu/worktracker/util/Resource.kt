package com.berkaykurtoglu.worktracker.util

sealed class Resource<T>(val data : T? =null,val message : String?=null){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String,data: T? = null) : Resource<T>(message = message,data = data)
    class Loading<T>(message: String?= null,data: T?=null) : Resource<T>(data,message)
}

