package com.berkaykurtoglu.worktracker.presentation.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(SignInScreenState())
    val state : State<SignInScreenState> = _state

    fun signIn(
        email : String,
        password : String
    ){

        if (email.isBlank() or password.isBlank()){
            _state.value = _state.value.copy(isLoading = false,
                    error = "Please fill email and password"
                )
            return
        }

        useCases.signIn(email, password).onEach {
            when(it) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(isLoading = false, isSignedIn = true)
                }

                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = true, isSignedIn = false)
                }

                is Resource.Error ->{
                    _state.value = _state.value.copy(
                        isLoading = false, error = it.message ?: "Error", isSignedIn = false)
                }

            }
        }.launchIn(viewModelScope)



    }



}