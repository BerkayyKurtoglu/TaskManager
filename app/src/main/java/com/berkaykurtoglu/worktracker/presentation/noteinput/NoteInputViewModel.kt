package com.berkaykurtoglu.worktracker.presentation.noteinput

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteInputViewModel @Inject constructor(
    private val useCases : UseCases
) : ViewModel() {


    private val _state = mutableStateOf(NoteInputState())
    val state : State<NoteInputState> = _state


    fun addATask(
        title: String,
        body: String,
        deadLine: String,
        backGround: String,
    ){
        val user = useCases.getCurrentUser()
        user?.let {
            val task = Task(title, body, deadLine, backGround, user = it)
            useCases.addATask(task).onEach {

                when(it) {

                    is Resource.Success ->{
                        _state.value = _state.value.copy(isLoading = false, isSuccesfull = true)
                    }

                    is Resource.Loading ->{
                        _state.value = _state.value.copy(isLoading = true, isSuccesfull = false)
                    }

                    is Resource.Error ->{
                        _state.value = _state.value.copy(isLoading = false, errorMsg = it.message ?: "Error")
                    }

                }
            }.launchIn(viewModelScope)

        } ?: {
            _state.value = _state.value.copy(errorMsg = "Please, sign out and retry again :(")
        }

    }



}