package example.singletontest.data

import example.singletontest.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface UserTasks {
    fun loadAll(): Flow<List<Task>>
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
}