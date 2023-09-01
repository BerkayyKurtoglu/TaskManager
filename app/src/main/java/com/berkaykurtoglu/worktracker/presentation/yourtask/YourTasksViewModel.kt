package com.berkaykurtoglu.worktracker.presentation.yourtask

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
class YourTasksViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(YourTaskState())
    val state : State<YourTaskState> = _state

    init {
        useCases.getCurrentUser()?.let {
            getUnmarkedTasks()
        }
    }

    fun getMarkedTasks(){
        useCases.getCurrentUser()?.let {
            useCases.getCurrentUsersMarkedTasks(it).onEach {

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

    fun getUnmarkedTasks(){
        useCases.getCurrentUser()?.let {

            useCases.getCurrentUsersUnmarkedTasksUseCase(it).onEach {

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






}