package com.berkaykurtoglu.worktracker.domain.usecases

import com.berkaykurtoglu.worktracker.data.SignInRepository

class SignInUseCase(
    private val repository: SignInRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ) = repository.signInUserWithEmailAndPassword(email, password)
}