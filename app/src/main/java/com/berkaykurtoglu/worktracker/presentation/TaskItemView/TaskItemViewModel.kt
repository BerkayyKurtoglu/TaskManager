package com.berkaykurtoglu.worktracker.presentation.TaskItemView

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
class TaskItemViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(TaskItemViewState())
    val state : State<TaskItemViewState> = _state

    fun getCurrentUser() = useCases.getCurrentUser()

    fun deleteATask(
        documentId : String
    )=

        useCases.deleteATask(documentId).onEach {

            when(it) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(isLoading = false, error = "",isCompleted = true)
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(isLoading = false, error = it.message!!,isCompleted = false)
                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = true, error = "",isCompleted = false)
                }
            }

        }.launchIn(viewModelScope)



}