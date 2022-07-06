package example.singletontest.presentation.screen_room

import example.singletontest.domain.model.Task

data class RoomState(
    val isLoading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val editableTaskDescription: String = ""
)