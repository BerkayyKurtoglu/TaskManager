package com.berkaykurtoglu.worktracker.domain.usecases

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentUserUseCase(
    private val firebase : Firebase
) {

    operator fun invoke() = firebase.auth.currentUser?.email

}