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


    val user = useCases.getCurrentUser()

    private fun addATask(
        task: Task
    ){
        user?.let {
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

    fun onEvent(
        event : NoteInputEvent
    ){
        when(event){
            is NoteInputEvent.addATask ->{
                user?.let {
                    val task = Task(event.title, event.body, event.deadLine, event.backGround, user =it)
                    addATask(task)
                }
            }
        }

    }



}