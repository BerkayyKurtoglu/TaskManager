package com.berkaykurtoglu.worktracker.presentation.friendstask

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
class FriendsTaskViewModel @Inject constructor(
    private val useCases: UseCases
) :ViewModel() {

    private val _state = mutableStateOf(FriendsTaskState())
    val state : State<FriendsTaskState> = _state

    init {
        getFriendsUnmarkedTasks()
    }

    fun getFriendsMarkedTasks(){

        useCases.getFriendsMarkedTasksUseCase().onEach {

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

    fun getFriendsUnmarkedTasks(){

        useCases.getFriendsUnmarkedTasksUseCase().onEach {

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