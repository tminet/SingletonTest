package example.singletontest.presentation.screen_room

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.singletontest.domain.model.Task
import example.singletontest.domain.usecase.DeleteTaskUseCase
import example.singletontest.domain.usecase.GetTasksUseCase
import example.singletontest.domain.usecase.InsertTaskUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    var state by mutableStateOf(value = RoomState())

    init {
        getTasks()
    }

    fun onAction(action: RoomAction) {
        when (action) {
            is RoomAction.SaveTask -> saveTask()
            is RoomAction.ChangeTaskDescription -> state = state.copy(
                editableTaskDescription = action.description
            )
            is RoomAction.FinishTask -> finishTask(task = action.task)
        }
    }

    private fun getTasks() = viewModelScope.launch {
        getTasksUseCase().collectLatest { tasks ->
            state = state.copy(
                isLoading = false,
                tasks = tasks
            )
        }
    }

    private fun saveTask() = viewModelScope.launch {
        if (state.editableTaskDescription.isNotBlank()) {
            insertTaskUseCase(task = Task(description = state.editableTaskDescription))
            state = state.copy(editableTaskDescription = "")
        }
    }

    private fun finishTask(task: Task) = viewModelScope.launch {
        deleteTaskUseCase(task = task)
    }
}