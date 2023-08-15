package com.berkaykurtoglu.worktracker.presentation.mainscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.presentation.search.SearchScreenState
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchScreenState())
    val state : State<SearchScreenState> = _state

    init {
        getTasksOnce()
    }

    fun clearTheList(){
        _state.value = _state.value.copy(task = listOf())
    }

    fun filterTasks(
        list: List<Task>,
        query : String
    ) {
        useCases.searchTasksUseCase(list, query).onEach {
            when(it) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(isLoading = false, task = it.data!!)
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(error = it.message!!, isLoading = false, task = listOf())
                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = true, task = listOf(), error = "")
                }
            }
        }.launchIn(viewModelScope)

    }


    private fun getTasksOnce() {
        useCases.getTasksOnceUseCase().onEach {
            when(it) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(taskForOnceList = it.data!!, taskForOnceLoading = false)
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(taskForOnceList = listOf(), taskForOnceLoading = false, error = it.message!!)
                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(taskForOnceLoading = true, taskForOnceList = listOf())
                }
            }

        }.launchIn(viewModelScope)
    }


    fun signOut() = useCases.signOutUseCase()





}