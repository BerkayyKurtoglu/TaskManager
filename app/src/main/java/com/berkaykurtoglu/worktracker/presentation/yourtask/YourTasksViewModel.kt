package com.berkaykurtoglu.worktracker.presentation.yourtask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterCategorie
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class YourTasksViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(YourTaskState())
    val state : State<YourTaskState> = _state

    val chipIndex : MutableState<FilterCategorie> = mutableStateOf(FilterCategorie.UnMarkedChip)

    init {
        useCases.getCurrentUser()?.let {
            getUnmarkedTasks()
        }
    }

    fun getMarkedTasks(isPrivate : Boolean = false){
        useCases.getCurrentUser()?.let {
            useCases.getCurrentUsersMarkedTasks(it, isPrivate = isPrivate).onEach {

                when(it) {
                    is Resource.Success ->{
                        _state.value = _state.value.copy(isLoading = false, tasks = it.data!!)
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(isLoading = false, error = it.message!!)
                    }
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(isLoading = true, error = "")
                    }
                    else ->{}
                }

            }.launchIn(viewModelScope)
        }
    }

    fun getUnmarkedTasks(isPrivate: Boolean = false){
        useCases.getCurrentUser()?.let {

            useCases.getCurrentUsersUnmarkedTasksUseCase(it, isPrivate = isPrivate).onEach {

                when(it) {
                    is Resource.Success ->{
                        _state.value = _state.value.copy(isLoading = false, tasks = it.data!!)
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(isLoading = false, error = it.message!!)
                    }
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(isLoading = true, error = "")
                    }
                    else ->{}
                }

            }.launchIn(viewModelScope)

        }

    }

    fun onEvent(
        event : YourTaskEvent
    ){
        when(event) {
            is YourTaskEvent.PrivateFilterSelection ->{
                if (_state.value.isDone) getMarkedTasks(event.isPrivate)
                else getUnmarkedTasks(event.isPrivate)
            }
            is YourTaskEvent.DoneSelection ->{
                _state.value = _state.value.copy(isDone = true)
            }
            is YourTaskEvent.UnDoneSelection ->{
                _state.value = _state.value.copy(isDone = false)
            }
        }
    }






}