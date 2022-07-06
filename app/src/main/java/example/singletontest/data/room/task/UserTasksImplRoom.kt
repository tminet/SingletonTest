package example.singletontest.data.room.task

import example.singletontest.data.UserTasks
import example.singletontest.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserTasksImplRoom @Inject constructor(
    private val taskDao: TaskDao
) : UserTasks {
    override fun loadAll(): Flow<List<Task>> =
        taskDao.loadAll().map { it.toTasks() }

    override suspend fun insert(task: Task) =
        taskDao.insert(taskEntity = task.toTaskEntity())

    override suspend fun delete(task: Task) =
        taskDao.delete(taskEntity = task.toTaskEntity())

    private fun List<TaskEntity>.toTasks() = map { taskEntity ->
        Task(
            description = taskEntity.description,
            createdAt = taskEntity.createdAt
        )
    }

    private fun Task.toTaskEntity() = TaskEntity(
        description = description,
        createdAt = createdAt
    )
}