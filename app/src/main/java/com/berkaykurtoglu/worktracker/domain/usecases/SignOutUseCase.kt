package com.berkaykurtoglu.worktracker.domain.usecases

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Singleton

@Singleton
class SignOutUseCase(
    private val firebase : Firebase
) {

    operator fun invoke() {
        firebase.auth.signOut()
    }

}