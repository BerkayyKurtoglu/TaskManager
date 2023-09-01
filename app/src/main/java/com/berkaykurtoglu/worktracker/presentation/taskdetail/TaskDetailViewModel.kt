package com.berkaykurtoglu.worktracker.presentation.taskdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkaykurtoglu.worktracker.domain.model.Comment
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.domain.usecases.UseCases
import com.berkaykurtoglu.worktracker.util.Constants
import com.berkaykurtoglu.worktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state : State<TaskDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.DOCUMENT_ID_ARG)?.let {
            getTaskDetail(it)
        }
    }

    fun getTaskDetail(
        documentId: String
    ){

        useCases.getTaskDetailUseCase(documentId).onEach {
            when(it) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(isLoading = false, task = it.data!!, error = "")
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(isLoading = false, error = it.message!!, task = Task())
                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = true, task = Task(), error = "")
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getCurrentUser() : String? = useCases.getCurrentUser()

    private fun addAComment(
        comment: Comment,
        documentId: String
    ){

        useCases.addACommentUseCase(comment, documentId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value =
                        _state.value.copy(isLoading = true, isCommentUploaded = false, error = "", isDoneCompleted = false)
                }
                is Resource.Success ->{
                    _state.value =
                        _state.value.copy(isLoading = false, isCommentUploaded = true, error = "", isDoneCompleted = false)
                }
                is Resource.Error ->{
                    _state.value =
                        _state.value.copy(isLoading = false, error = it.message!!, isCommentUploaded = false, isDoneCompleted = false)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun markTaskAsDone(
        documentId: String
    ){
        useCases.markAsDoneUseCase(documentId).onEach {

            when (it) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true, isCommentUploaded = false, error = "", isDoneCompleted = false)
                }
                is Resource.Success ->{
                    _state.value = _state.value.copy(isLoading = false, isCommentUploaded = true, error = "", isDoneCompleted = true)
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(isLoading = false, error = it.message!!, isCommentUploaded = false, isDoneCompleted = false)
                }

            }

        }.launchIn(viewModelScope)

    }

    fun onEvent(
        event : TaskDetailEvent
    ){
        when(event) {
            is TaskDetailEvent.AddComment ->{
                addAComment(event.comment,event.taskDocumentId)
            }
            is TaskDetailEvent.MarkAsDone ->{
                markTaskAsDone(event.taskDocumentId)
            }
        }
    }





}