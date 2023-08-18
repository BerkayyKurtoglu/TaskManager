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

    private val _state = mutableStateOf(MainScreenState())
    val state : State<MainScreenState> = _state

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

    fun getUserInfo(
        email : String
    ){
        useCases.getUserInfo(email).onEach {

            when(it){

                is Resource.Loading ->{
                    _state.value = _state.value.copy(dialogIsLoading = true)
                }
                is Resource.Success ->{
                    it.data?.let {
                        _state.value = _state.value.copy(dialogUser = it, dialogIsLoading = false)
                    } ?: {
                        _state.value = _state.value.copy(dialogError = "Something went wrong",
                            dialogIsLoading = false)
                    }
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(dialogError = it.message ?:"Something went wrong", dialogIsLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTasksOnce() {
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


    fun onEvent(
        event : MainEvent
    ){
        when(event){
            is MainEvent.Profile ->{
                getUserEmail()?.let {
                    getUserInfo(it)
                } ?: {
                    _state.value = _state.value.copy(dialogError = "Something went wrong")
                }
            }
        }
    }

    fun signOut() = useCases.signOutUseCase()

    fun getUserEmail() = useCases.getCurrentUser()





}