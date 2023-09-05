package com.berkaykurtoglu.worktracker.presentation.friendstask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.presentation.friendstask.screen.components.FilterCategorie
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class FriendsTaskViewModel @Inject constructor(
    private val useCases: UseCases
) :ViewModel() {

    private val _state = mutableStateOf(FriendsTaskState())
    val state : State<FriendsTaskState> = _state

    val chipIndex : MutableState<FilterCategorie> = mutableStateOf(FilterCategorie.UnMarkedChip)

    init {

        getFriendsUnmarkedTasks()
    }

    fun getFriendsMarkedTasks(){

        useCases.getCurrentUser()?.let {
            useCases.getFriendsMarkedTasksUseCase(it).onEach {

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
        }?:{
            _state.value = _state.value.copy(isLoading = false, error = "Current user" +
                    "Could not get, try later", tasks = listOf()
            )
        }

    }

    fun getFriendsUnmarkedTasks(){

        useCases.getCurrentUser()?.let {
            useCases.getFriendsUnmarkedTasksUseCase(it).onEach {

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
        }?:{
            _state.value = _state.value.copy(isLoading = false, error = "Current user" +
                    "Could not get, try later", tasks = listOf()
            )
        }

    }


}