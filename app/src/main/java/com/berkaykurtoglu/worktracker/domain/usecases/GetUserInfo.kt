package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.MainRepository
import javax.inject.Singleton

@Singleton
class GetUserInfo(
    private val repository: MainRepository
) {

    operator fun invoke(
        email : String
    ) = repository.getUsersInfo(email)

}