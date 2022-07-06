package example.singletontest.presentation.screen_room

import example.singletontest.domain.model.Task

sealed class RoomAction {
    object SaveTask : RoomAction()
    data class ChangeTaskDescription(val description: String) : RoomAction()
    data class FinishTask(val task: Task) : RoomAction()
}