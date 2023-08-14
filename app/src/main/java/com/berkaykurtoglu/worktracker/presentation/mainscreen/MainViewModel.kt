package com.berkaykurtoglu.worktracker.presentation.mainscreen

import androidx.lifecycle.ViewModel
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


    fun signOut() = useCases.signOutUseCase()



}